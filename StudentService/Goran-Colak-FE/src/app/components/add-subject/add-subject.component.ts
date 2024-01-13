import { Professor } from 'src/app/models/professor.model';
import { ProfessorService } from './../../services/professor.service';
import { Component, OnInit } from '@angular/core';
import { Subject } from 'src/app/models/subject.model';
import { SubjectService } from 'src/app/services/subject.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-add-subject',
  templateUrl: './add-subject.component.html',
  styleUrls: ['./add-subject.component.css'],
})

export class AddSubjectComponent implements OnInit {

  subject: Subject = new Subject();
  professors: Professor[] = [];
  addForm: FormGroup;
  isValidFormSubmitted = false;

  semesterList = [
    { name: "Summer" },
    { name: "Winter" }
  ];

  constructor(private subjectService: SubjectService,
    private professorService: ProfessorService,
    private toastrService: ToastrService,
    private router: Router,
    public formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.getProfessors();
    this.buildForm(this.subject);
  }

  buildForm(subject: Subject) {
    this.addForm = this.formBuilder.group({
      name: ['', [Validators.required, Validators.minLength]],
      description: ['',],
      noofesp: [0, [Validators.required]],
      yearofstudy: [0, [Validators.required]],
      semester: ['', [Validators.required]]
    });
  }

  onAddFormSubmit() {
    this.isValidFormSubmitted = false;
    if (this.addForm.valid) {
      this.isValidFormSubmitted = true;

      let newSubject: Subject = this.addForm.value;
      this.subjectService.createSubject(newSubject).subscribe(data => {
        console.log(data);
        this.goToSubjectList();
      },
      error => (
        this.toastrService.error(error.error.message)
      ));
    }

    else {
      console.log('This form is not valid!');
      return;
    }
  }

  getProfessors() {
    this.professorService.getProfessorList().subscribe(data => {
      this.professors = data;
    });
  }

  goToSubjectList() {
    this.router.navigate(['/subjects']);
  }

  // Access formcontrols getter

  get name() { return this.addForm.get('name'); }

  get noofesp() { return this.addForm.get('noofesp'); }

  get yearofstudy() { return this.addForm.get('yearofstudy'); }

  get semester() { return this.addForm.get('semester'); }

}
