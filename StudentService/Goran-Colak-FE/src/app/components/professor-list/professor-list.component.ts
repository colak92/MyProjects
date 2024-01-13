import { Component, OnInit } from '@angular/core';
import { Professor } from 'src/app/models/professor.model';
import { ProfessorService } from 'src/app/services/professor.service';
import { Router } from '@angular/router';
import { Sort } from '@angular/material/sort';
import { ConfirmDialogComponent } from 'src/app/components/confirm-dialog/confirm-dialog.component';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-professor-list',
  templateUrl: './professor-list.component.html',
  styleUrls: ['./professor-list.component.css']
})

export class ProfessorListComponent implements OnInit {

  professors: Professor[] = [];
  sortedData: Professor[] = [];
  page = 1;
  count = 0;
  pageSize = 3;
  pageSizes = [3, 6, 9];
  sortBy = 'id';
  sortDir = 'asc';
  dialogRef: MatDialogRef<ConfirmDialogComponent> | null;

  constructor(private professorService: ProfessorService,
              private toastrService: ToastrService,
              private dialog: MatDialog,
              private router: Router) { }

  ngOnInit(): void {
    this.getProfessors();
  }

  addProfessor(){
    this.router.navigate(['add-professor']);
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

  getProfessors(): void {
    const params = this.getRequestParams(this.page, this.pageSize, this.sortBy, this.sortDir);
    this.professorService.getPageAndSortList(params)
      .subscribe({
        next: ( data = [] || null) => {
        if (data === null){
          this.professors = [];
          this.count = 0;
        }
        else{
          const { professors, totalItems } = data;
          this.professors = professors;
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
    const professorlist = this.professors;
    if (!sort.active || sort.direction === '') {
      this.sortedData = professorlist;
      return;
    }

    const params = this.getRequestParams(this.page, this.pageSize, sort.active, sort.direction);
    this.professorService.getPageAndSortList(params)
      .subscribe({
        next: (data) => {
          const { professors, totalItems } = data;
          this.professors = professors;
          this.count = totalItems;
          console.log(data);
          this.sortedData = data;
        },
        error: (err) => {
          console.log(err);
        }
      });
  }

  professorDetails(id: number){
    this.router.navigate(['professor-details', id]);
  }

  updateProfessor(id: number){
    this.router.navigate(['edit-professor', id]);
  }

  deleteProfessor(id: number){
    this.professorService.deleteProfessor(id).subscribe({
      next: (data) => {
        console.log(data);
        this.getProfessors();
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
        this.deleteProfessor(id);
      }
      this.dialogRef = null;
    });
  }

  handlePageChange(event: number): void {
    this.page = event;
    this.getProfessors();
  }

  handlePageSizeChange(event: any): void {
    this.pageSize = event.target.value;
    this.page = 1;
    this.getProfessors();
  }
}
