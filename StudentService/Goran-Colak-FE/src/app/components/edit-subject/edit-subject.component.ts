import { Component, OnInit } from '@angular/core';
import { Subject } from 'src/app/models/subject.model';
import { SubjectService } from 'src/app/services/subject.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-edit-subject',
  templateUrl: './edit-subject.component.html',
  styleUrls: ['./edit-subject.component.css']
})

export class EditSubjectComponent implements OnInit {

  id: number;
  subject: Subject = new Subject();
  editForm: FormGroup;
  isValidFormSubmitted = false;

  semesterList = [
    { name: "Summer" },
    { name: "Winter" }
  ];

  constructor(private subjectService: SubjectService, 
              private route: ActivatedRoute,
              private router: Router,
              public formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.loadSubject();

    this.editForm = this.formBuilder.group({
      name: (['', [Validators.required, Validators.minLength]]),
      description: [''],
      noofesp: [0, [Validators.required]],
      yearofstudy: [0, [Validators.required]],
      semester: ['', [Validators.required]]
    });
  }

  loadSubject(){
    this.id = this.route.snapshot.params['id'];
    this.subject = new Subject();
    this.subjectService.getSubjectById(this.id).subscribe( data => {
      this.subject = data;
      this.buildForm(data);
    });
  }

  buildForm(subject: Subject){
    this.editForm = this.formBuilder.group({
      name: [subject?.name, [Validators.required, Validators.minLength]],
      description: [subject?.description],
      noofesp: [subject?.noofesp, [Validators.required]],
      yearofstudy: [subject?.yearofstudy, [Validators.required]],
      semester: [subject?.semester, [Validators.required]]
    });
  }

  onEditFormSubmit() {
		this.isValidFormSubmitted = false;
		if (this.editForm.valid) {
			this.isValidFormSubmitted = true;

      let editedSubject: Subject = this.editForm.value;
      this.subjectService.updateSubject(this.id, editedSubject).subscribe( data =>{
        console.log(data);
        this.goToSubjectList();
      }, error => console.log(error));

		} else {
			return;
		}
	}

  goToSubjectList(){
    this.router.navigate(['/subjects']);
  }

  // Access formcontrols getter

  get name() { return this.editForm.get('name'); }

  get noofesp() { return this.editForm.get('noofesp'); }

  get yearofstudy() { return this.editForm.get('yearofstudy'); }

  get semester() { return this.editForm.get('semester'); }

}
