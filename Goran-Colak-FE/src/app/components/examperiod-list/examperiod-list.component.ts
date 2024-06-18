import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ExamPeriod } from 'src/app/models/examperiod.model';
import { ExamPeriodService } from 'src/app/services/examperiod.service';

@Component({
  selector: 'app-examperiod-list',
  templateUrl: './examperiod-list.component.html',
  styleUrls: ['./examperiod-list.component.css']
})

export class ExamPeriodListComponent implements OnInit {

  examperiods: ExamPeriod[] = [];

  constructor(private examPeriodService: ExamPeriodService, private router: Router) { }

  ngOnInit(): void {
    this.getExamPeriods();
  }

  addExamPeriod(){
    this.router.navigate(['add-examperiod']);
  }

  getExamPeriods(){
    this.examPeriodService.getExamPeriodList().subscribe(data => {
      this.examperiods = data;
    });
  }

  updateExamPeriod(id: number){
    this.router.navigate(['edit-examperiod', id]);
  }

  deleteExamPeriod(id: number){
    this.examPeriodService.deleteExamPeriod(id).subscribe( data => {
      console.log(data);
      this.getExamPeriods();
    })
  }
}
