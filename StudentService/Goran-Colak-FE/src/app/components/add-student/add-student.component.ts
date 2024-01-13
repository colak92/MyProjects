import { Component, OnInit } from '@angular/core';
import { Student } from 'src/app/models/student.model';
import { StudentService } from 'src/app/services/student.service';
import { Router } from '@angular/router';
import { City } from 'src/app/models/city.model';
import { CityService } from 'src/app/services/city.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-add-student',
  templateUrl: './add-student.component.html',
  styleUrls: ['./add-student.component.css']
})
export class AddStudentComponent implements OnInit {

  student: Student = new Student();
  cities: City[] = [];
  addForm: FormGroup;
  isValidFormSubmitted = false;

  constructor(private studentService: StudentService, 
              private cityService: CityService,
              private toastrService: ToastrService,
              private router: Router,
              public formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.getCities();
    this.buildForm(this.student);
  }

  buildForm(student: Student) {
    this.addForm = this.formBuilder.group({
      indexnumber: ['', [Validators.required, Validators.minLength, Validators.maxLength]],
      indexyear: [0, [Validators.required, Validators.min, Validators.max]],
      firstname: ['', [Validators.required, Validators.minLength]],
      lastname: ['', [Validators.required, Validators.minLength]],
      jmbg: [0, [Validators.required, Validators.minLength, Validators.maxLength]],
      email: ['', [Validators.email]],
      address: ['', [Validators.minLength]],
      cityId: [0, [Validators.required]],
      currentyearofstudy: [0, [Validators.required]]
    });
  }

  onAddFormSubmit() {
		this.isValidFormSubmitted = false;
		if (this.addForm.valid) {
      console.log('This form is valid!');
			this.isValidFormSubmitted = true;

      let newStudent: Student = this.addForm.value;
      this.studentService.createStudent(newStudent).subscribe( data =>{
        console.log(data);
        this.goToStudentList();
      },
      error => (
        this.toastrService.error(error.error.message)
      ));

		} else {
      console.log('This form is not valid!');
			return;
		}
	}

  getCities(){
    this.cityService.getCityList().subscribe(data => {
      this.cities = data;
    });
  }

  goToStudentList(){
    this.router.navigate(['/students']);
  }

  // Access form controls getter - for validation

  get indexnumber() { return this.addForm.get('indexnumber'); }

  get indexyear() { return this.addForm.get('indexyear'); }

  get firstname() { return this.addForm.get('firstname'); }

  get lastname() { return this.addForm.get('lastname'); }

  get jmbg() { return this.addForm.get('jmbg'); }

  get email() { return this.addForm.get('email'); }

  get address() { return this.addForm.get('address'); }

  get cityId() { return this.addForm.get('cityId'); }

  get currentyearofstudy() { return this.addForm.get('currentyearofstudy'); }
  
}
