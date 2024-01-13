import { StudentService } from './../../services/student.service';
import { Exam } from 'src/app/models/exam.model';
import { Student } from './../../models/student.model';
import { ExamPeriod } from 'src/app/models/examperiod.model';
import { SubjectService } from 'src/app/services/subject.service';
import { ExamPeriodService } from 'src/app/services/examperiod.service';
import { Component, OnInit } from '@angular/core';
import { ExamReg } from 'src/app/models/examreg.model';
import { ExamRegService } from 'src/app/services/examreg.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subject } from 'src/app/models/subject.model';
import { ExamService } from 'src/app/services/exam.service';

@Component({
  selector: 'app-save-examreg',
  templateUrl: './save-examreg.component.html',
  styleUrls: ['./save-examreg.component.css']
})
export class SaveExamRegComponent implements OnInit {

  id: number;
  examreg: ExamReg = new ExamReg();
  students: Student[] = [];
  examperiods: ExamPeriod[] = [];
  exams: Exam[] = [];
  subjects: Subject[] = [];
  saveForm: FormGroup;
  isValidFormSubmitted = false;

  constructor(private examRegService: ExamRegService,
              private studentService: StudentService,
              private examPeriodService: ExamPeriodService,
              private examService: ExamService,
              private subjectService: SubjectService,
              private route: ActivatedRoute,
              private router: Router,
              public formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.getStudents();
    this.getExamPeriods();
    this.getExams();
    this.getSubjects();

    this.loadExamReg();

    this.saveForm = this.formBuilder.group({
      student: [null, ],
      jmbg: [0, ],
      examperiod: [null, ],
      examstart: [null, ],
      examend: [null, ],
      exam: [null, ],
      examdate: [null, ],
      registered: [null, [Validators.required]],
      subject: [null, ],
      comment: ['', ]
    });
  }

  loadExamReg(){
    this.id = this.route.snapshot.params['id'];
    this.examRegService.getExamRegById(this.id).subscribe( data => {
      this.examreg = data;
      this.buildForm(data);
    });
  }

  buildForm(examreg: ExamReg) {
    this.saveForm = this.formBuilder.group({
      student: [examreg?.exam.student, ],
      jmbg: [examreg?.exam.student?.jmbg, ],
      examperiod: [examreg?.exam.examperiod, ],
      examstart: [examreg?.exam.examperiod?.examstart, ],
      examend: [examreg?.exam.examperiod?.examend, ],
      exam: [examreg?.exam, ],
      examdate: [examreg?.exam?.examdate, ],
      registered: [examreg?.exam?.registered, [Validators.required]],
      subject: [examreg?.exam.subject, ],
      comment: [examreg?.comment, ]
    });
  }

  onSaveFormSubmit() {
		this.isValidFormSubmitted = false;
		if (this.saveForm.valid) {
			this.isValidFormSubmitted = true;

      let editedExamReg: ExamReg = this.saveForm.value;
      editedExamReg.exam.registered = this.registered?.value;
      this.examRegService.saveExamReg(this.id, editedExamReg).subscribe( data =>{
        this.goToExamRegList();
      }, error => console.log(error));

		} else {
			return;
		}
	}

  getStudents(){
    this.studentService.getStudentList().subscribe(data => {
      this.students = data;
    });
  }
  
  getExamPeriods(){
    this.examPeriodService.getExamPeriodList().subscribe(data => {
      this.examperiods = data;
    });
  }

  getSubjects(){
    this.subjectService.getSubjectList().subscribe(data => {
      this.subjects = data;
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


  get student() { return this.saveForm.get('student'); }

  get jmbg() { return this.saveForm.get('jmbg'); }

  get examperiod() { return this.saveForm.get('examperiod'); }

  get examstart() { return this.saveForm.get('examstart'); }

  get examend() { return this.saveForm.get('examend'); }

  get exam() { return this.saveForm.get('exam'); }

  get examdate() { return this.saveForm.get('examdate'); }

  get registered() { return this.saveForm.get('registered'); }

  get subject() { return this.saveForm.get('subject'); }

  get comment() { return this.saveForm.get('comment'); }

  compareStudents(studentOne: Student, studentTwo: Student) {
    return studentOne?.id === studentTwo?.id;
  }

  compareExamPeriods(examPeriodOne: ExamPeriod, examPeriodTwo: ExamPeriod) {
    return examPeriodOne?.id === examPeriodTwo?.id;
  }

  compareExams(examOne: Exam, examTwo: Exam) {
    return examOne?.id === examTwo?.id;
  }

  compareSubjects(subjectOne: Subject, subjectTwo: Subject) {
    return subjectOne?.id === subjectTwo?.id;
  }
  
}
