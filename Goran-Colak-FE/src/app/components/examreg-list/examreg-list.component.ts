import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ExamReg } from 'src/app/models/examreg.model';
import { ExamRegService } from 'src/app/services/examreg.service';

@Component({
  selector: 'app-examreg-list',
  templateUrl: './examreg-list.component.html',
  styleUrls: ['./examreg-list.component.css']
})

export class ExamRegListComponent implements OnInit {

  examregs: ExamReg[] = [];
  isDisabled = false;

  constructor(private examRegService: ExamRegService, private router: Router) { }

  ngOnInit(): void {
    this.getExamRegs();
  }

  getExamRegs(){
    this.examRegService.getExamRegList().subscribe(data => {
      this.examregs = data;
      console.log(data);
    });
  }

  examRegistration(id: number){
    this.router.navigate(['save-examreg', id]);
  }

  deleteExamReg(id: number){
    this.examRegService.deleteExamReg(id).subscribe( data => {
      console.log(data);
      this.getExamRegs();
    })
  }
}
