import { StudentService } from '../../services/student.service';
import { Exam } from 'src/app/models/exam.model';
import { Student } from '../../models/student.model';
import { Component, OnInit } from '@angular/core';
import { ExamReg } from 'src/app/models/examreg.model';
import { ExamRegService } from 'src/app/services/examreg.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ExamService } from 'src/app/services/exam.service';
import { ToastrService } from 'ngx-toastr';
import { TokenStorageService } from 'src/app/services/token-storage.service';

@Component({
  selector: 'app-add-examreg',
  templateUrl: './add-examreg.component.html',
  styleUrls: ['./add-examreg.component.css']
})
export class AddExamRegComponent implements OnInit {

  examreg: ExamReg = new ExamReg();
  students: Student[] = [];
  exams: Exam[] = [];
  examRegForm: FormGroup;
  isValidFormSubmitted = false;

  constructor(private examRegService: ExamRegService,
              private studentService: StudentService,
              private examService: ExamService,
              private toastrService: ToastrService,
              private tokenStorageService: TokenStorageService,
              private router: Router,
              public formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.getStudents();
    this.getExams();
    this.buildForm(this.examreg);

    const user = this.tokenStorageService.getUser();
    console.log(user);

    // find user email, then find student and set ID

    if (user.roles.includes('STUDENT')){
      console.log('test');
      this.examreg.studentId = 142;
      this.loadStudent();
    }
  }

  loadStudent(){
    let id = 143;
    this.examRegService.getExamRegById(id).subscribe( data => {
      this.examreg = data;
      this.buildForm(data);
    });
  }

  buildForm(examreg: ExamReg) {
    this.examRegForm = this.formBuilder.group({
      grade: [5, [Validators.min, Validators.max, Validators.maxLength]],
      passed: [false, [Validators.required]],
      comment: ['', ],
      studentId: [0 || this.examreg.studentId, [Validators.required]],
      examId: [0, [Validators.required]],
    });
  }

  onAddFormSubmit() {
    this.isValidFormSubmitted = false;
		if (this.examRegForm.valid) {

			this.isValidFormSubmitted = true;
      let newExamReg: ExamReg = this.examRegForm.value;
      this.examRegService.createExamReg(newExamReg).subscribe( data =>{
        console.log(data);
        this.goToExamRegList();
      },
      error => (
        this.toastrService.error(error.error.message)
      ));

		} else {
			return;
		}
  }

  getStudents(){
    this.studentService.getStudentList().subscribe(data => {
      this.students = data;
    });
  }
  
  getExams(){
    this.examService.getExamList().subscribe(data => {
      this.exams = data;
    });
  }

  goToExamRegList(){
    this.router.navigate(['/examregs']);
  }

  // Access form controls getter - for validation

  get grade() { return this.examRegForm.get('grade'); }

  get passed() { return this.examRegForm.get('passed'); }

  get comment() { return this.examRegForm.get('comment'); }

  get studentId() { return this.examRegForm.get('studentId'); }

  get examId() { return this.examRegForm.get('examId'); }

}
