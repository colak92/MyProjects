import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { UserService } from 'src/app/services/user.service';
import { User } from 'src/app/models/user.model';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})

export class EditProfileComponent implements OnInit {

  id: number;
  user: User = new User();
  editForm: FormGroup;
  isValidFormSubmitted = false;
  errorMessage = '';

  constructor(private userService: UserService, 
              private route: ActivatedRoute,
              private router: Router,
              private toastrService: ToastrService,
              public formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.loadProfile();

    this.editForm = this.formBuilder.group({
      username: ['', [Validators.required, Validators.minLength]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength]],
      confirmPassword: ['', [Validators.required, Validators.minLength]]
    });
  }

  loadProfile(){
    this.id = this.route.snapshot.params['id'];
    this.userService.getProfileById(this.id).subscribe( data => {
      this.user = data;
      this.buildForm(data);
    });
  }

  buildForm(user: any){
    this.editForm = this.formBuilder.group({
      username: [user?.username, [Validators.required]],
      email: [user?.email, [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength]],
      confirmPassword: ['', [Validators.required, Validators.minLength]]
    });
  }

  onEditFormSubmit(): void {
		this.isValidFormSubmitted = false;
		if (this.editForm.valid) {
      
			this.isValidFormSubmitted = true;
      let editedUser: any = this.editForm.value;
      this.userService.updateProfile(this.id, editedUser).subscribe( data =>{
        this.goToProfile();
      },
      
      error => (
        this.toastrService.error(error.error.message)
      ));

    } else {
			return;
		}
	}

  goToProfile(){
    this.router.navigate(['/profile', this.id]);
  }

  get username() { return this.editForm.get('username'); }

  get email() { return this.editForm.get('email'); }

  get password() { return this.editForm.get('password'); }

  get confirmPassword() { return this.editForm.get('confirmPassword'); }

}
