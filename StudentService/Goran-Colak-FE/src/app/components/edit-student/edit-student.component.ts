import { Component, OnInit } from '@angular/core';
import { Student } from 'src/app/models/student.model';
import { StudentService } from 'src/app/services/student.service';
import { ActivatedRoute, Router } from '@angular/router';
import { City } from 'src/app/models/city.model';
import { CityService } from 'src/app/services/city.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-edit-student',
  templateUrl: './edit-student.component.html',
  styleUrls: ['./edit-student.component.css']
})

export class EditStudentComponent implements OnInit {

  id: number;
  student: Student = new Student();
  cityList: City[];
  editForm: FormGroup;
  isValidFormSubmitted = false;
  submitted = false;

  constructor(private studentService: StudentService, 
              private cityService: CityService,
              private toastrService: ToastrService,
              private route: ActivatedRoute,
              private router: Router,
              public formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.getCities();
    this.loadStudent();

    this.editForm = this.formBuilder.group({
      indexnumber: ['', [Validators.required, Validators.minLength, Validators.maxLength]],
      indexyear: [0, [Validators.required, Validators.min, Validators.max]],
      firstname: ['', [Validators.required, Validators.minLength]],
      lastname: ['', [Validators.required, Validators.minLength]],
      jmbg: [0, [Validators.required, Validators.minLength, Validators.maxLength]],
      email: ['', [Validators.email]],
      address: ['', [Validators.minLength]],
      cityId: [0, [Validators.required]],
      cityName: ['', [Validators.required]],
      currentyearofstudy: [0, [Validators.required]]
    });
  }

  loadStudent(){
    this.id = this.route.snapshot.params['id'];
    this.studentService.getStudentById(this.id).subscribe( data => {
      this.student = data;
      console.log(data);
      this.buildForm(data);
    });
  }

  buildForm(student: Student) {
    this.editForm = this.formBuilder.group({
      indexnumber: [student?.indexnumber, [Validators.required, Validators.minLength, Validators.maxLength]],
      indexyear: [student?.indexyear, [Validators.required, Validators.min, Validators.max]],
      firstname: [student?.firstname, [Validators.required, Validators.minLength]],
      lastname: [student?.lastname, [Validators.required, Validators.minLength]],
      jmbg: [student?.jmbg, [Validators.required, Validators.minLength, Validators.maxLength]],
      email: [student?.email, [Validators.email]],
      address: [student?.address, [Validators.minLength]],
      cityId: [student?.cityId, [Validators.required]],
      cityName: [student?.cityName, [Validators.required]],
      currentyearofstudy: [student?.currentyearofstudy, [Validators.required]]
    });
  }

  onEditFormSubmit() {
    console.log(this.editForm.value);

		this.isValidFormSubmitted = false;
		if (this.editForm.valid) {
			this.isValidFormSubmitted = true;

      let editedStudent: Student = this.editForm.value;

      this.studentService.updateStudent(this.id, editedStudent).subscribe( data =>{
        console.log(data);
        this.goToStudentList();
      },
      error => (
        this.toastrService.error(error.error.message)
      ));

		} else {
			return;
		}
	}

  changeCity(e: any) {
    console.log(e.target.value);
    this.cityId?.setValue(e.target.value, {
      onlySelf: true,
    });
  }

  getcompanyid(event: Event) {
    console.log('Selected Company: ', event);
  }

  getCities(){
    this.cityService.getCityList().subscribe(data => {
      this.cityList = data;
    });
  }

  goToStudentList(){
    this.router.navigate(['/students']);
  }  

  // Access form controls getter - for validation

  get indexnumber() { return this.editForm.get('indexnumber'); }

  get indexyear() { return this.editForm.get('indexyear'); }

  get firstname() { return this.editForm.get('firstname'); }

  get lastname() { return this.editForm.get('lastname'); }

  get jmbg() { return this.editForm.get('jmbg'); }

  get email() { return this.editForm.get('email'); }

  get address() { return this.editForm.get('address'); }

  get cityId() { return this.editForm.get('cityId'); }

  get cityName() { return this.editForm.get('cityName'); }

  get currentyearofstudy() { return this.editForm.get('currentyearofstudy'); }

}
