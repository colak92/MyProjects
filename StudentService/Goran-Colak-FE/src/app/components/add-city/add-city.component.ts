import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { City } from 'src/app/models/city.model';
import { CityService } from 'src/app/services/city.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-add-city',
  templateUrl: './add-city.component.html',
  styleUrls: ['./add-city.component.css']
})
export class AddCityComponent implements OnInit {

  city: City = new City();
  addForm: FormGroup;
  isValidFormSubmitted = false;

  constructor(private cityService: CityService, 
              private router: Router,
              public formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.buildForm(this.city);
  }

  buildForm(city: City) {
    this.addForm = this.formBuilder.group({
      postalcode: ['0', Validators.required],
      name: ['', Validators.required]
    });
  }

  onAddFormSubmit() {
    this.isValidFormSubmitted = false;
		if (this.addForm.valid) {

			this.isValidFormSubmitted = true;
      let newCity: City = this.addForm.value;
      this.cityService.createCity(newCity).subscribe( data =>{
        console.log(data);
        this.goToCityList();
      }, error => console.log(error));

		} else {
			return;
		}
  }

  goToCityList(){
    this.router.navigate(['/cities']);
  }

  // Access form controls getter - for validation

  get postalcode() { return this.addForm.get('postalcode'); }

  get name() { return this.addForm.get('name'); }
  
}
