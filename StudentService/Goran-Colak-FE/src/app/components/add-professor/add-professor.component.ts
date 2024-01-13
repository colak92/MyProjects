import { SubjectService } from './../../services/subject.service';
import { Subject } from 'src/app/models/subject.model';
import { Component, OnInit } from '@angular/core';
import { Professor } from 'src/app/models/professor.model';
import { ProfessorService } from 'src/app/services/professor.service';
import { Router } from '@angular/router';
import { City } from 'src/app/models/city.model';
import { CityService } from 'src/app/services/city.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Title } from 'src/app/models/title.model';
import { TitleService } from 'src/app/services/title.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-add-professor',
  templateUrl: './add-professor.component.html',
  styleUrls: ['./add-professor.component.css']
})
export class AddProfessorComponent implements OnInit {

  professor: Professor = new Professor();
  cities: City[] = [];
  titles: Title[] = [];
  subjects: Subject[] = [];
  addForm: FormGroup;
  isValidFormSubmitted = false;

  constructor(private professorService: ProfessorService, 
              private cityService: CityService,
              private titleService: TitleService,
              private subjectService: SubjectService,
              private toastrService: ToastrService,
              private router: Router,
              public formBuilder: FormBuilder) { 
              
  }

  ngOnInit(): void {
    this.getCities();
    this.getTitles();
    this.getSubjects();
    this.buildForm(this.professor);
  }

  buildForm(professor: Professor){
    this.addForm = this.formBuilder.group({
      firstname: ['',[Validators.required, Validators.minLength]],
      lastname: ['', [Validators.required, Validators.minLength]],
      email: ['', [Validators.email]],
      address: ['', [Validators.minLength]],
      phone: ['', [Validators.pattern('[- +()0-9]+'), Validators.minLength]],
      reelectiondate: [null, [Validators.required]],
      cityId: [0,],
      titleId: [0, [Validators.required]],
      subjectList: [null, [Validators.required]]
    });
  }

  onAddFormSubmit() {
    console.log(this.isValidFormSubmitted);

		this.isValidFormSubmitted = false;
		if (this.addForm.valid) {
			this.isValidFormSubmitted = true;

      let newProfessor: Professor = this.addForm.value;
      this.professorService.createProfessor(newProfessor).subscribe( data =>{
        console.log(data);
        this.goToProfessorList();
      },
      error => (
        this.toastrService.error(error.error.message)
      ));

		} else {
      console.log('This form is not valid!');
			return;
		}
	}

  getCities(){
    this.cityService.getCityList().subscribe(data => {
      this.cities = data;
    });
  }

  getTitles(){
    this.titleService.getTitleList().subscribe(data => {
      this.titles = data;
    });
  }

  getSubjects(){
    this.subjectService.getSubjectList().subscribe(data => {
      this.subjects = data;
    });
  }

  goToProfessorList(){
    this.router.navigate(['/professors']);
  }

  // Access form controls getter - for validation

  get firstname() { return this.addForm.get('firstname'); }

  get lastname() { return this.addForm.get('lastname'); }

  get email() { return this.addForm.get('email'); }

  get address() { return this.addForm.get('address'); }

  get phone() { return this.addForm.get('phone'); }

  get reelectiondate() { return this.addForm.get('reelectiondate'); }

  get cityId() { return this.addForm.get('cityId'); }

  get titleId() { return this.addForm.get('titleId'); }

  get subjectList() { return this.addForm.get('subjectList'); }


  
}
