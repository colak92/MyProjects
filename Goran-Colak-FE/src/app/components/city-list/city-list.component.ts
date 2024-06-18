import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { City } from 'src/app/models/city.model';
import { CityService } from 'src/app/services/city.service';

@Component({
  selector: 'app-city-list',
  templateUrl: './city-list.component.html',
  styleUrls: ['./city-list.component.css']
})

export class CityListComponent implements OnInit {

  cities: City[] = [];

  constructor(private cityService: CityService, private router: Router) { }

  ngOnInit(): void {
    this.getCities();
  }

  addCity(){
    this.router.navigate(['add-city']);
  }

  getCities(){
    this.cityService.getCityList().subscribe(data => {
      this.cities = data;
    });
  }

  deleteCity(id: number){
    this.cityService.deleteCity(id).subscribe( data => {
      console.log(data);
      this.getCities();
    })
  }
  
}
