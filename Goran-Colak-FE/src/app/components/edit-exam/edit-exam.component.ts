import { ProfessorService } from './../../services/professor.service';
import { SubjectService } from './../../services/subject.service';
import { ExamPeriodService } from 'src/app/services/examperiod.service';
import { Professor } from 'src/app/models/professor.model';
import { Student } from 'src/app/models/student.model';
import { Subject } from 'src/app/models/subject.model';
import { ExamPeriod } from './../../models/examperiod.model';
import { Component, OnInit, Input } from '@angular/core';
import { Exam } from 'src/app/models/exam.model';
import { ExamService } from 'src/app/services/exam.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-edit-exam',
  templateUrl: './edit-exam.component.html',
  styleUrls: ['./edit-exam.component.css']
})

export class EditExamComponent implements OnInit {

  id: number;
  exam: Exam = new Exam();
  examPeriodList: ExamPeriod[] = [];
  subjectList: Subject[] = [];
  professorList: Professor[] = [];
  editForm: FormGroup;
  isValidFormSubmitted = false;

  constructor(private examService: ExamService, 
              private examPeriodService: ExamPeriodService,
              private subjectService: SubjectService,
              private professorService: ProfessorService,
              private toastrService: ToastrService,
              private route: ActivatedRoute,
              private router: Router,
              public formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.getExamPeriods();
    this.getSubjects();
    this.getProfessors();
    this.loadExam();

    this.editForm = this.formBuilder.group({
      name: ['', [Validators.required]],
      examdate: [null, [Validators.required]],
      examPeriodId: [0, [Validators.required]],
      examPeriodName: ['', [Validators.required]],
      subjectId: [0, [Validators.required]],
      subjectName: ['', [Validators.required]],
      professorId: [0, [Validators.required]],
      professorFirstName: ['', [Validators.required]],
      professorLastName: ['', [Validators.required]]
    });
    
  }

  loadExam(){
    this.id = this.route.snapshot.params['id'];
    this.examService.getExamById(this.id).subscribe( data => {
      this.exam = data;
      console.log('Test:' + data);
      this.buildForm(data);
    });
  }

  buildForm(exam: Exam) {
    this.editForm = this.formBuilder.group({
      name: [exam?.name, [Validators.required]],
      examdate: [exam?.examdate, [Validators.required]],
      examPeriodId: [exam?.examPeriodId, [Validators.required]],
      examPeriodName: [exam?.examPeriodName, [Validators.required]],
      subjectId: [exam?.subjectId, [Validators.required]],
      subjectName: [exam?.subjectName, [Validators.required]],
      professorId: [exam?.professorId, [Validators.required]],
      professorFirstName: [exam?.professorFirstName, [Validators.required]],
      professorLastName: [exam?.professorLastName, [Validators.required]]
    });
  }

  onEditFormSubmit() {
		this.isValidFormSubmitted = false;
		if (this.editForm.valid) {

			this.isValidFormSubmitted = true;
      let editedExam: Exam = this.editForm.value;
      this.examService.updateExam(this.id, editedExam).subscribe( data =>{
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
      this.subjectList = data;
    });
  }

  updateProfessors(): void {
    this.professorService.getProfessorListForSubject(this.subjectId?.value).subscribe(data => {
      this.professorList = data;
    });
  }

  getExamPeriods(){
    this.examPeriodService.getActiveExamPeriodList().subscribe(data => {
      this.examPeriodList = data;
    });
  }

  getSubjects(){
    this.subjectService.getSubjectList().subscribe(data => {
      this.subjectList = data;
    });
  }

  getProfessors(){
    this.professorService.getProfessorList().subscribe(data => {
      this.professorList = data;
    });
  }

  goToExamList(){
    this.router.navigate(['/exams']);
  }

  // Access form controls getter - for validation

  get name() { return this.editForm.get('name'); }

  get examdate() { return this.editForm.get('examdate'); }

  get examPeriodId() { return this.editForm.get('examPeriodId'); }

  get examPeriodName() { return this.editForm.get('examPeriodName'); }

  get subjectId() { return this.editForm.get('subjectId'); }

  get subjectName() { return this.editForm.get('subjectName'); }

  get professorId() { return this.editForm.get('professorId'); }

  get professorFirstName() { return this.editForm.get('professorFirstName'); }

  get professorLastName() { return this.editForm.get('professorLastName'); }

}
