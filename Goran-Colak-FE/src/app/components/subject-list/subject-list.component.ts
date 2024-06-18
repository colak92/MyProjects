import { Component, OnInit } from '@angular/core';
import { Subject } from 'src/app/models/subject.model';
import { SubjectService } from 'src/app/services/subject.service';
import { Router } from '@angular/router';
import { Sort } from '@angular/material/sort';
import { ConfirmDialogComponent } from 'src/app/components/confirm-dialog/confirm-dialog.component';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-subject-list',
  templateUrl: './subject-list.component.html',
  styleUrls: ['./subject-list.component.css']
})

export class SubjectListComponent implements OnInit {

  subjects: Subject[] = [];
  sortedData: Subject[] = [];
  page = 1;
  count = 0;
  pageSize = 3;
  pageSizes = [3, 6, 9];
  sortBy = 'id';
  sortDir = 'asc';
  dialogRef: MatDialogRef<ConfirmDialogComponent> | null;

  constructor(private subjectService: SubjectService,
              private toastrService: ToastrService,
              private dialog: MatDialog,
              private router: Router) { }

  ngOnInit(): void {
    this.getSubjects();
  }

  addSubject(){
    this.router.navigate(['add-subject']);
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

  getSubjects(){
    const params = this.getRequestParams(this.page, this.pageSize, this.sortBy, this.sortDir);
    this.subjectService.getPageAndSortList(params)
      .subscribe({
        next: ( data = [] || null) => {
          if (data === null){
            this.subjects = [];
            this.count = 0;
          }
          else{
            const { subjects, totalItems } = data;
            this.subjects = subjects;
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
    const subjectList = this.subjects;
    if (!sort.active || sort.direction === '') {
      this.sortedData = subjectList;
      return;
    }

    const params = this.getRequestParams(this.page, this.pageSize, sort.active, sort.direction);
    this.subjectService.getPageAndSortList(params)
      .subscribe({
        next: (data) => {
          const { subjects, totalItems } = data;
          this.subjects = subjects;
          this.count = totalItems;
          console.log(data);
          this.sortedData = data;
        },
        error: (err) => {
          console.log(err);
        }
      });
  }

  subjectDetails(id: number){
    this.router.navigate(['subject-details', id]);
  }

  updateSubject(id: number){
    this.router.navigate(['edit-subject', id]);
  }

  deleteSubject(id: number){
    this.subjectService.deleteSubject(id).subscribe({
      next: (data) => {
        console.log(data);
        this.getSubjects();
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
        this.deleteSubject(id);
      }
      this.dialogRef = null;
    });
  }

  handlePageChange(event: number): void {
    this.page = event;
    this.getSubjects();
  }

  handlePageSizeChange(event: any): void {
    this.pageSize = event.target.value;
    this.page = 1;
    this.getSubjects();
  }
}