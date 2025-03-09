import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Title } from 'src/app/models/title.model';
import { TitleService } from 'src/app/services/title.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-add-title',
  templateUrl: './add-title.component.html',
  styleUrls: ['./add-title.component.css']
})
export class AddTitleComponent implements OnInit {

  title: Title = new Title();
  titles: Title[] = [];
  addForm: FormGroup;
  isValidFormSubmitted = false;

  constructor(private titleService: TitleService, 
              private router: Router,
              public formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.addForm = this.formBuilder.group({
      name: ['', Validators.required]
    });
  }

  onAddFormSubmit() {
    this.isValidFormSubmitted = false;
		if (this.addForm.valid) {
			this.isValidFormSubmitted = true;
		} else {
			return;
		}

    let newTitle: Title = this.addForm.value;
    this.titleService.createTitle(newTitle).subscribe( data =>{
      console.log(data);
      this.goToTitleList();
    }, error => console.log(error));
  }

  getTitles(){
    this.titleService.getTitleList().subscribe(data => {
      this.titles = data;
    });
  }

  goToTitleList(){
    this.router.navigate(['/titles']);
  }

  get name() { return this.addForm.get('name'); }
  
}
