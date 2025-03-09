import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Exam } from 'src/app/models/exam.model';
import { StudentService } from 'src/app/services/student.service';
import { Student } from 'src/app/models/student.model';
import { ExamReg } from 'src/app/models/examreg.model';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ExamService } from 'src/app/services/exam.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-item-dialog',
  templateUrl: './examreg-dialog.component.html',
  styleUrls: ['./examreg-dialog.component.css']
})

export class ExamRegDialogComponent implements OnInit {

  examReg: ExamReg = new ExamReg();
  students: Student[] = [];
  selectedExams: Exam[] = [];
  selectedStudent: Student;
  isReadOnly = true;  // This makes the select read-only

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any[],
    private studentService: StudentService,
    private examService: ExamService,
    private toastrService: ToastrService,
    public dialogRef: MatDialogRef<ExamRegDialogComponent>,
    private router: Router,
    public formBuilder: FormBuilder
  ) { }

  ngOnInit(): void {
    this.getStudents();
  }

  onRegisterExamFormSubmit() {
    if (this.selectedStudent != null && this.selectedExams != null) {
      
      this.examService.registerExams(this.selectedStudent, this.selectedExams).subscribe(data => {
        console.log(data);
        this.dialogRef.close();
        this.goToExamRegList();
      },
        error => (
          this.toastrService.error(error.error.message)
        ));

    } else {
      console.log('This data is not valid!');
      return;
    }
  }

  getStudents() {
    this.selectedExams = this.data;
    console.log('Selected exams: ' + this.selectedExams)
    this.studentService.getStudentList().subscribe(data => {
      this.students = data;
    });
  }

  goToExamRegList() {
    this.router.navigate(['/examregs']);
  }


}