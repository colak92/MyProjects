import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ExamPeriod } from 'src/app/models/examperiod.model';
import { ExamPeriodService } from 'src/app/services/examperiod.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-add-examperiod',
  templateUrl: './add-examperiod.component.html',
  styleUrls: ['./add-examperiod.component.css']
})
export class AddExamPeriodComponent implements OnInit {

  examperiod: ExamPeriod = new ExamPeriod();
  addForm: FormGroup;
  isValidFormSubmitted = false;

  statusList = [
    {id: 0, name: "Inactive" },
    {id: 1, name: "Active" }
  ];

  constructor(private examPeriodService: ExamPeriodService,
              private toastrService: ToastrService,
              private router: Router,
              public formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.buildForm(this.examperiod);
  }

  buildForm(examperiod: ExamPeriod) {
    this.addForm = this.formBuilder.group({
      name: ['', Validators.required],
      examstart: [null, Validators.required],
      examend: [null, Validators.required],
      status: ['', Validators.required]
    });
  }

  onAddFormSubmit() {
    this.isValidFormSubmitted = false;
		if (this.addForm.valid) {
			this.isValidFormSubmitted = true;

      let newExamPeriod: ExamPeriod = this.addForm.value;
      this.examPeriodService.createExamPeriod(newExamPeriod).subscribe( (data: any) =>{
        this.goToExamPeriodList();
      }, 
      error => (
        this.toastrService.error(error.error.message)
      ));

		} else {
			return;
		}
  }

  goToExamPeriodList(){
    this.router.navigate(['/examperiods']);
  }

  // Access form controls getter - for validation

  get name() { return this.addForm.get('name'); }

  get examstart() { return this.addForm.get('examstart'); }

  get examend() { return this.addForm.get('examend'); }

  get status() { return this.addForm.get('status'); }
  
}
