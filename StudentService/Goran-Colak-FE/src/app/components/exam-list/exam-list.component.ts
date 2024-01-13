import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Exam } from 'src/app/models/exam.model';
import { ExamService } from 'src/app/services/exam.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-exam-list',
  templateUrl: './exam-list.component.html',
  styleUrls: ['./exam-list.component.css']
})

export class ExamListComponent implements OnInit {

  exams: Exam[] = [];

  constructor(private examService: ExamService,
    private toastrService: ToastrService,
    private router: Router) { }

  ngOnInit(): void {
    this.getExams();
  }

  addExam() {
    this.router.navigate(['add-exam']);
  }

  getExams() {
    this.examService.getExamList().subscribe(data => {
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
}
