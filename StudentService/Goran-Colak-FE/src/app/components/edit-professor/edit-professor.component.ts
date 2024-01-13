import { Subject } from 'src/app/models/subject.model';
import { SubjectService } from './../../services/subject.service';
import { Component, OnInit } from '@angular/core';
import { Professor } from 'src/app/models/professor.model';
import { ProfessorService } from 'src/app/services/professor.service';
import { ActivatedRoute, Router } from '@angular/router';
import { City } from 'src/app/models/city.model';
import { CityService } from 'src/app/services/city.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Title } from 'src/app/models/title.model';
import { TitleService } from 'src/app/services/title.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-edit-professor',
  templateUrl: './edit-professor.component.html',
  styleUrls: ['./edit-professor.component.css']
})

export class EditProfessorComponent implements OnInit {

  id: number;
  professor: Professor = new Professor();
  cityList: City[];
  titleList: Title[] = [];
  subjectData: Subject[] = [];
  editForm: FormGroup;
  isValidFormSubmitted = false;

  constructor(private professorService: ProfessorService, 
              private cityService: CityService,
              private titleService: TitleService,
              private subjectService: SubjectService,
              private toastrService: ToastrService,
              private route: ActivatedRoute,
              private router: Router,
              public formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.getCities();
    this.getTitles();
    this.getSubjects();
    this.loadProfessor();

    this.editForm = this.formBuilder.group({
      firstname: ['', [Validators.required, Validators.minLength]],
      lastname: ['', [Validators.required, Validators.minLength]],
      email: ['', [Validators.email]],
      address: ['', [Validators.minLength]],
      phone: ['', [Validators.pattern('[- +()0-9]+'), Validators.minLength]],
      reelectiondate: [null, [Validators.required]],
      cityId: [null],
      cityName: [null],
      titleId: [null, [Validators.required]],
      titleName: [null, [Validators.required]],
      subjectList: [null, [Validators.required]]
    });
  }

  loadProfessor(){
    this.id = this.route.snapshot.params['id'];
    this.professorService.getProfessorById(this.id).subscribe( data => {
      this.professor = data;
      this.buildForm(data);
    });
  }

  buildForm(professor: Professor){
    this.editForm = this.formBuilder.group({
      firstname: [professor?.firstname, [Validators.required]],
      lastname: [professor?.lastname, [Validators.required]],
      email: [professor?.email, [Validators.email]],
      address: [professor?.address, [Validators.minLength]],
      phone: [professor?.phone, [Validators.pattern('[- +()0-9]+'), Validators.minLength]],
      reelectiondate: [professor?.reelectiondate, [Validators.required]],
      cityId: [professor?.cityId],
      cityName: [professor.cityName],
      titleId: [professor?.titleId, [Validators.required]],
      titleName: [professor?.titleName, [Validators.required]],
      subjectList: [professor?.subjectList, [Validators.required]]
    });
  }

  onEditFormSubmit() {
		this.isValidFormSubmitted = false;
		if (this.editForm.valid) {
			this.isValidFormSubmitted = true;

      let editedProfessor: Professor = this.editForm.value;
      this.professorService.updateProfessor(this.id, editedProfessor).subscribe( data =>{
        console.log(data);
        this.goToProfessorList();
      },
      error => (
        this.toastrService.error(error.error.message)
      ));

		} else {
			return;
		}
	}

  getCities(){
    console.log('Loading cities ...')
    this.cityService.getCityList().subscribe(data => {
      this.cityList = data;
    });
  }

  getTitles(){
    this.titleService.getTitleList().subscribe(data => {
      this.titleList = data;
    });
  }

  getSubjects(){
    this.subjectService.getSubjectList().subscribe(data => {
      this.subjectData = data;
    });
  }

  goToProfessorList(){
    this.router.navigate(['/professors']);
  }

  // Access formcontrols getter

  get firstname() { return this.editForm.get('firstname'); }

  get lastname() { return this.editForm.get('lastname'); }

  get email() { return this.editForm.get('email'); }

  get address() { return this.editForm.get('address'); }

  get phone() { return this.editForm.get('phone'); }

  get reelectiondate() { return this.editForm.get('reelectiondate'); }

  get cityId() { return this.editForm.get('cityId'); }

  get cityName() { return this.editForm.get('cityName'); }

  get titleId() { return this.editForm.get('titleId'); }

  get titleName() { return this.editForm.get('titleName'); }

  get subjectList() { return this.editForm.get('subjectList'); }

}
