import { Component, OnInit, Input } from '@angular/core';
import { ExamPeriod } from 'src/app/models/examperiod.model';
import { ExamPeriodService } from 'src/app/services/examperiod.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-edit-examperiod',
  templateUrl: './edit-examperiod.component.html',
  styleUrls: ['./edit-examperiod.component.css']
})

export class EditExamPeriodComponent implements OnInit {

  id: number;
  examperiod: ExamPeriod = new ExamPeriod();
  editForm: FormGroup;
  isValidFormSubmitted = false;

  constructor(private examPeriodService: ExamPeriodService,
              private toastrService: ToastrService, 
              private route: ActivatedRoute,
              private router: Router,
              public formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.loadExamPeriod();

    this.editForm = this.formBuilder.group({
      name: ['', [Validators.required]],
      examstart: [null, [Validators.required]],
      examend: [null, [Validators.required]],
      status: [null, ]
    });
  }

  loadExamPeriod(){
    this.id = this.route.snapshot.params['id'];
    this.examPeriodService.getExamPeriodById(this.id).subscribe( data => {
      this.examperiod = data;
      this.buildForm(data);
    });
  }

  buildForm(examperiod: ExamPeriod) {
    this.editForm = this.formBuilder.group({
      name: [examperiod?.name, [Validators.required]],
      examstart: [examperiod?.examstart, [Validators.required]],
      examend: [examperiod?.examend, [Validators.required]],
      status: [examperiod?.status, ]
    });
  }

  onEditFormSubmit() {
		this.isValidFormSubmitted = false;
		if (this.editForm.valid) {
			this.isValidFormSubmitted = true;

      let editedExamPeriod: ExamPeriod = this.editForm.value;
      this.examPeriodService.updateExamPeriod(this.id, editedExamPeriod).subscribe( data =>{
        console.log(data);
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

  get name() { return this.editForm.get('name'); }

  get examstart() { return this.editForm.get('examstart'); }

  get examend() { return this.editForm.get('examend'); }

}
