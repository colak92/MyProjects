import { Component, OnInit, Inject } from '@angular/core';
import { Router } from '@angular/router';
import { Exam } from 'src/app/models/exam.model';
import { ExamService } from 'src/app/services/exam.service';
import { ToastrService } from 'ngx-toastr';
import { TokenStorageService } from 'src/app/services/token-storage.service';
import { MatDialog } from '@angular/material/dialog';
import { ExamRegDialogComponent } from 'src/app/components/examreg-dialog/examreg-dialog.component';

@Component({
  selector: 'app-exam-list',
  templateUrl: './exam-list.component.html',
  styleUrls: ['./exam-list.component.css']
})

export class ExamListComponent implements OnInit {

  exams: Exam[] = [];
  selectedExams: Exam[] = [];
  btnRegExamDisabled: boolean = true;

  constructor(private examService: ExamService,
    private toastrService: ToastrService,
    private router: Router,
    private tokenStorageService: TokenStorageService,
    private dialog: MatDialog
  ) { }

  ngOnInit(): void {
    const user = this.tokenStorageService.getUser();

    if (user.roles.includes('ADMIN')){
      this.getExams();
    }

    if (user.roles.includes('PROFESSOR')){
      this.getProfessorExams(user.id);
    }
  }

  addExam() {
    this.router.navigate(['add-exam']);
  }

  getExams() {
    this.examService.getExamList().subscribe(data => {
      this.exams = data;
    });
  }

  getProfessorExams(id: number){
    this.examService.getProfessorExamList(id).subscribe(data => {
      this.exams = data;
    });
  }

  updateExam(id: number) {
    this.router.navigate(['edit-exam', id]);
  }

  deleteExam(id: number) {
    this.examService.deleteExam(id).subscribe(data => {
      console.log(data);
      this.getExams();
    },
      error => (
        this.toastrService.error(error.error.message)
      ));
  }

  isSelected(exam: any): boolean {
    return this.selectedExams.indexOf(exam) !== -1;
  }

  onChange(value: any): void {
    if (this.selectedExams.includes(value)) {
      this.selectedExams = this.selectedExams.filter((item) => item !== value);
      console.log('Remove selected exam');

      if (this.selectedExams.length == 0){
        this.btnRegExamDisabled = true;
      }
    } 
    else {
      this.selectedExams.push(value);
      console.log('Insert selected exam');

      if (this.selectedExams.length > 0){
        this.btnRegExamDisabled = false;
      }
    }
    console.log('Selected exams (OnChange):' + this.selectedExams);
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(ExamRegDialogComponent, {
      width: '600px',
      height: '300px',
      data: this.selectedExams
    });
  }

}