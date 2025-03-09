import { Component, OnInit, Input } from '@angular/core';
import { ExamRegService } from 'src/app/services/examreg.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { ExamReg } from 'src/app/models/examreg.model';

@Component({
  selector: 'app-enter-examgrade',
  templateUrl: './enter-examgrade.component.html',
  styleUrls: ['./enter-examgrade.component.css']
})

export class EnterExamGradeComponent implements OnInit {

  id: number;
  examReg: ExamReg = new ExamReg();
  examGradeForm: FormGroup;
  isValidFormSubmitted = false;

  constructor(private examRegService: ExamRegService,
              private toastrService: ToastrService, 
              private route: ActivatedRoute,
              private router: Router,
              public formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.loadExamReg();

    this.examGradeForm = this.formBuilder.group({
      comment: ['', [Validators.required]],
      grade: [0, [Validators.required]]
    });
  }

  loadExamReg(){
    this.id = this.route.snapshot.params['id'];
    this.examRegService.getExamRegById(this.id).subscribe( data => {
      this.examReg = data;
      this.buildForm(data);
    });
  }

  buildForm(examReg: ExamReg) {
    this.examGradeForm = this.formBuilder.group({
      comment: [examReg?.comment, [Validators.required]],
      grade: [examReg?.grade, [Validators.required]]
    });
  }

  onExamGradeFormSubmit() {
		this.isValidFormSubmitted = false;
		if (this.examGradeForm.valid) {
			this.isValidFormSubmitted = true;

      let editedExamReg: ExamReg = this.examGradeForm.value;
      this.examRegService.enterExamGrade(this.id, editedExamReg).subscribe( data =>{
        console.log(data);
        this.goToExamRegList();
      },
      
      error => (
        this.toastrService.error(error.error.message)
      ));

		} else {
			return;
		}
	}

  goToExamRegList(){
    this.router.navigate(['/examregs']);
  }

  // Access form controls getter - for validation

  get comment() { return this.examGradeForm.get('comment'); }

  get grade() { return this.examGradeForm.get('grade'); }

}
