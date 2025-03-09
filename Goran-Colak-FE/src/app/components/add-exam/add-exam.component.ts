import { Student } from './../../models/student.model';
import { ProfessorService } from './../../services/professor.service';
import { SubjectService } from './../../services/subject.service';
import { ExamPeriodService } from './../../services/examperiod.service';
import { Professor } from './../../models/professor.model';
import { Subject } from './../../models/subject.model';
import { ExamPeriod } from './../../models/examperiod.model';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Exam } from 'src/app/models/exam.model';
import { ExamService } from 'src/app/services/exam.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-add-exam',
  templateUrl: './add-exam.component.html',
  styleUrls: ['./add-exam.component.css']
})
export class AddExamComponent implements OnInit {

  exam: Exam = new Exam();
  addForm: FormGroup;
  examperiods: ExamPeriod[] = [];
  subjects: Subject[] = [];
  professors: Professor[] = [];
  isValidFormSubmitted = false;

  constructor(private examService: ExamService,
              private examPeriodService: ExamPeriodService,
              private subjectService: SubjectService,
              private professorService: ProfessorService,
              private toastrService: ToastrService,
              private router: Router,
              public formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.getExamPeriods();
    this.getSubjects();
    this.getProfessors();
    this.buildForm(this.exam);
  }

  buildForm(exam: Exam) {
    this.addForm = this.formBuilder.group({
      name: ['', Validators.required],
      examdate: [null, Validators.required],
      examPeriodId: [0, Validators.required],
      subjectId: [0, [Validators.required]],
      professorId: [0, [Validators.required]]
    });
  }

  onAddFormSubmit() {
    this.isValidFormSubmitted = false;
		if (this.addForm.valid) {

			this.isValidFormSubmitted = true;
      let newExam: Exam = this.addForm.value;
      this.examService.createExam(newExam).subscribe( data =>{
        console.log(data);
        this.goToExamList();
      },
      error => (
        this.toastrService.error(error.error.message)
      ));

		} else {
			return;
		}
  }

  updateSubjects(): void {
    this.subjectService.getSubjectListForProfessor(this.professorId?.value).subscribe(data => {
      this.subjects = data;
    });
  }

  updateProfessors(): void {
    this.professorService.getProfessorListForSubject(this.subjectId?.value).subscribe(data => {
      this.professors = data;
    });
  }

  getExamPeriods(){
    this.examPeriodService.getActiveExamPeriodList().subscribe(data => {
      this.examperiods = data;
    });
  }

  getSubjects(){
    this.subjectService.getSubjectList().subscribe(data => {
      this.subjects = data;
    });
  }

  getProfessors(){
    this.professorService.getProfessorList().subscribe(data => {
      this.professors = data;
    });
  }

  goToExamList(){
    this.router.navigate(['/exams']);
  }

  // Access form controls getter - for validation

  get name() { return this.addForm.get('name'); }

  get examdate() { return this.addForm.get('examdate'); }

  get examPeriodId() { return this.addForm.get('examPeriodId'); }

  get subjectId() { return this.addForm.get('subjectId'); }

  get professorId() { return this.addForm.get('professorId'); }
  
}
