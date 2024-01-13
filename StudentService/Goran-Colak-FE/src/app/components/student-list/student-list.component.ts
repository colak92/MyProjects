import { Component, OnInit } from '@angular/core';
import { Student } from 'src/app/models/student.model';
import { StudentService } from 'src/app/services/student.service';
import { Router } from '@angular/router';
import { Sort } from '@angular/material/sort';
import { ConfirmDialogComponent } from 'src/app/components/confirm-dialog/confirm-dialog.component';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-student-list',
  templateUrl: './student-list.component.html',
  styleUrls: ['./student-list.component.css']
})

export class StudentListComponent implements OnInit {

  students: Student[] = [];
  sortedData: Student[] = [];
  page = 1;
  count = 0;
  pageSize = 3;
  pageSizes = [3, 6, 9];
  sortBy = 'id';
  sortDir = 'asc';
  dialogRef: MatDialogRef<ConfirmDialogComponent> | null;

  constructor(private studentService: StudentService,
              private toastrService: ToastrService,
              private dialog: MatDialog,
              private router: Router) { }

  ngOnInit(): void {
    this.getStudents();
  }

  addStudent() {
    this.router.navigate(['add-student']);
  }

  getRequestParams(page: number, pageSize: number, sortBy: string, sortDir: string): any {
    let params: any = {};

    if (page) {
      params[`page`] = page - 1;
    }

    if (pageSize) {
      params[`size`] = pageSize;
    }

    if (sortBy) {
      params[`sortBy`] = sortBy;
    }

    if (sortDir) {
      params[`sortDir`] = sortDir;
    }

    return params;
  }

  getStudents(): void {
    const params = this.getRequestParams(this.page, this.pageSize, this.sortBy, this.sortDir);
    this.studentService.getPageAndSortList(params)
      .subscribe({
        next: ( data = [] || null) => {
            if (data === null){
              this.students = [];
              this.count = 0;
            }
            else{
              const { students, totalItems } = data;
              this.students = students;
              this.count = totalItems;
              console.log(data);
            }
        },
        error: (err) => {
          console.log(err);
        }
      });
  }

  sortData(sort: Sort) {
    const studentlist = this.students;
    if (!sort.active || sort.direction === '') {
      this.sortedData = studentlist;
      return;
    }

    const params = this.getRequestParams(this.page, this.pageSize, sort.active, sort.direction);
    this.studentService.getPageAndSortList(params)
      .subscribe({
        next: (data) => {
          const { students, totalItems } = data;
          this.students = students;
          this.count = totalItems;
          this.sortedData = data;
        },
        error: (err) => {
          console.log(err);
        }
      });
  }

  studentDetails(id: number) {
    this.router.navigate(['student-details', id]);
  }

  updateStudent(id: number) {
    this.router.navigate(['edit-student', id]);
  }

  deleteStudent(id: number) {
    this.studentService.deleteStudent(id).subscribe({
      next: (data) => {
        console.log(data);
        this.getStudents();
      },
      error: (err) => {
        this.toastrService.error(err.error.message)
      }
    });
  }

  openConfirmDialog(id: number) {
    this.dialogRef = this.dialog.open(ConfirmDialogComponent, {
      disableClose: false
    });
    this.dialogRef.componentInstance.confirmMessage = "Are you sure you want to delete?"
    this.dialogRef.afterClosed().subscribe(result => {
      if(result) {
        this.deleteStudent(id);
      }
      this.dialogRef = null;
    });
  }

  handlePageChange(event: number): void {
    this.page = event;
    this.getStudents();
  }

  handlePageSizeChange(event: any): void {
    this.pageSize = event.target.value;
    this.page = 1;
    this.getStudents();
  }

}
