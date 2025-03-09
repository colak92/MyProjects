import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ExamReg } from 'src/app/models/examreg.model';
import { ExamRegService } from 'src/app/services/examreg.service';
import { TokenStorageService } from 'src/app/services/token-storage.service';

@Component({
  selector: 'app-examreg-list',
  templateUrl: './examreg-list.component.html',
  styleUrls: ['./examreg-list.component.css']
})

export class ExamRegListComponent implements OnInit {

  examregs: ExamReg[] = [];
  btnExamGradeDisabled: boolean = true;

  constructor(private examRegService: ExamRegService, 
              private router: Router,
              private tokenStorageService: TokenStorageService) { }

  ngOnInit(): void {
    const user = this.tokenStorageService.getUser();

    if (user.roles.includes('ADMIN') || user.roles.includes('PROFESSOR')){
      this.getExamRegs();
      this.btnExamGradeDisabled = false;
    }

    if (user.roles.includes('STUDENT')){
      this.getStudentExamRegs(user.id);
      this.btnExamGradeDisabled = true;
    }
  }

  getExamRegs(){
    this.examRegService.getExamRegList().subscribe(data => {
      this.examregs = data;
      console.log(data);
    });
  }

  getStudentExamRegs(id: number){
    this.examRegService.getStudentExamRegList(id).subscribe(data => {
      this.examregs = data;
    });
  }

  examRegistration(){
    this.router.navigate(['add-examreg']);
  }

  enterExamGrade(id: number){
    this.router.navigate(['enter-examgrade', id]);
  }

  deleteExamReg(id: number){
    this.examRegService.deleteExamReg(id).subscribe( data => {
      console.log(data);
      this.getExamRegs();
    })
  }
}
