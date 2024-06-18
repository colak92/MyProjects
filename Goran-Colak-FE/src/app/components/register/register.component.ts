import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

    registerForm!: FormGroup;
    isSubmitted = false;
    isSuccessful = false;
    isSignUpFailed = false;
    errorMessage = '';

    roleList = [
      {id: 2, name: "Professor" },
      {id: 3, name: "Student" }
    ];

    constructor(
      private formBuilder: FormBuilder,
      private authService: AuthService
    ) { }

    ngOnInit(): void {
      this.buildForm();
    }
  
    buildForm() {
      this.registerForm = this.formBuilder.group({
        username: ['', [Validators.required, Validators.minLength(3)]],
        email: ['', [Validators.required, Validators.email]],
        password: ['', [Validators.required, Validators.minLength(6)]],
        role: ['', [Validators.required]]
      });
    }

    onFormSubmit(): void {

      const { username, email, password, role } = this.registerForm.value;

      if (this.registerForm.valid) {
        this.isSubmitted = true;

        this.authService.register(username, email, password, role).subscribe({
          next: data => {
            console.log(data);
            this.isSuccessful = true;
            this.isSignUpFailed = false;
          },
          error: err => {
            this.errorMessage = err.error.message;
            this.isSignUpFailed = true;
          }
        });
      }
      else {
        return;
      }
  }

  // Access form controls getter - for validation

  get username() { return this.registerForm.get('username'); }
  get email() { return this.registerForm.get('email'); }
  get password() { return this.registerForm.get('password'); }
  get role() { return this.registerForm.get('role'); }

}
