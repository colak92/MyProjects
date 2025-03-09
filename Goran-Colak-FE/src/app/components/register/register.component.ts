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
  professorForm!: FormGroup;
  studentForm!: FormGroup;

  isSubmitted = false;
  isSuccessful = false;
  isSignUpFailed = false;

  errorMessage = '';
  isProfessorData = true;
  isStudentData = true;

  selectedRoleName: string | null = null;
  selectedTitleName: string | null = null;

  roleList = [
    { id: 1, name: "Professor" },
    { id: 2, name: "Student" }
  ];

  titleList = [
    { id: 1, name: "Assistant Professor" },
    { id: 2, name: "Associate Professor" },
    { id: 3, name: "Professor" },
    { id: 4, name: "Instructor" }
  ];

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService
  ) { }

  ngOnInit(): void {
    this.buildRegisterForm();
    this.buildProfessorForm();
    this.buildStudentForm();
  }

  buildRegisterForm() {
    this.registerForm = this.formBuilder.group({
      username: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(20)]],
      email: ['', [Validators.required, Validators.email, Validators.maxLength(50)]],
      password: ['', [Validators.required, Validators.minLength(6), Validators.maxLength(40)]],
      role: ['', [Validators.required]]
    });
  }

  buildProfessorForm() {
    this.professorForm = this.formBuilder.group({
      professorFirstName: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(15)]],
      professorLastName: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(15)]],
      reelectionDate: [null, [Validators.required]],
      title: ['', [Validators.required]]
    });

    // Optionally listen for form state changes
    this.professorForm.valueChanges.subscribe(value => {
      console.log('Professor form status: ' + this.professorForm.status);  // Check the form's validity status
    });
  }

  buildStudentForm() {
    this.studentForm = this.formBuilder.group({
      indexNumber: ['', [Validators.required, Validators.minLength(4), Validators.maxLength(4)]],
      indexYear: [2000, [Validators.required, Validators.min(2000), Validators.max(2100)]],
      studentFirstName: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(15)]],
      studentLastName: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(15)]],
      currentYearoFStudy: [1, [Validators.required, Validators.min(1), Validators.max(4)]]
    });

    // Optionally listen for form state changes
    this.studentForm.valueChanges.subscribe(value => {
      console.log('Student form status: ' + this.studentForm.status);  // Check the form's validity status
    });
  }

  onFormSubmit(): void {
    const { username, email, password, role } = this.registerForm.value;
    const { professorFirstName, professorLastName, reelectionDate, title } = this.professorForm.value;
    const { indexNumber, indexYear, studentFirstName, studentLastName, currentYearoFStudy } = this.studentForm.value;

    if (this.registerForm.valid && this.professorForm.valid) {
      this.isSubmitted = true;

      if (role.name === 'Professor') {
        this.authService.registerAsProfessor(username, email, password, role.name, professorFirstName, professorLastName, reelectionDate, title.name).subscribe({
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
    }

    if (this.registerForm.valid && this.studentForm.valid) {
      this.isSubmitted = true;

      if (role.name === 'Student') {
        this.authService.registerAsStudent(username, email, password, role.name, indexNumber, indexYear, studentFirstName, studentLastName, currentYearoFStudy).subscribe({
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
    }

    else {
      return;
    }
  }

  // Event handler to get the selected option's name
  onSelectionChange(event: any) {
    const selectedOption = event;  // This contains the selected object
    if (selectedOption) {
      this.selectedRoleName = selectedOption.name;  // Extract the 'name' of the selected object

      if (this.selectedRoleName === 'Student') {
        console.log('Student Data');
        this.isStudentData = false;
        this.isProfessorData = true;

        this.professorForm.markAsPristine();
        this.professorForm.markAsUntouched();
        this.professorForm.clearValidators();
        this.professorForm.reset();
        this.professorForm.updateValueAndValidity();  // Recalculate validity
      }

      else if (this.selectedRoleName === 'Professor') {
        console.log('Professor Data');
        this.isProfessorData = false;
        this.isStudentData = true;

        this.studentForm.markAsPristine();
        this.studentForm.markAsUntouched();
        this.studentForm.clearValidators();
        this.studentForm.reset();
        this.studentForm.updateValueAndValidity();  // Recalculate validity
      }

      else {
        console.log('Data not selected');
      }

      console.log('Selected Role Name:', this.selectedRoleName);
    }
  }

  // Access form controls getter - for validation

  get username() { return this.registerForm.get('username'); }
  get email() { return this.registerForm.get('email'); }
  get password() { return this.registerForm.get('password'); }
  get role() { return this.registerForm.get('role'); }

  get professorFirstName() { return this.professorForm.get('professorFirstName'); }
  get professorLastName() { return this.professorForm.get('professorLastName'); }
  get reelectionDate() { return this.professorForm.get('reelectionDate'); }
  get title() { return this.professorForm.get('title'); }

  get indexNumber() { return this.studentForm.get('indexNumber'); }
  get indexYear() { return this.studentForm.get('indexYear'); }
  get studentFirstName() { return this.studentForm.get('studentFirstName'); }
  get studentLastName() { return this.studentForm.get('studentLastName'); }
  get currentYearoFStudy() { return this.studentForm.get('currentYearoFStudy'); }

}
