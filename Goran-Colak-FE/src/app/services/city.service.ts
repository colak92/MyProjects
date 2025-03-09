import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { City } from '../models/city.model';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})

export class CityService {
  
  private apiUrl = environment.apiUrl; 
  private citiesUrl = this.apiUrl + "/cities";

  constructor(private http:HttpClient) { }

  getCityList(): Observable<City[]> {
    return this.http.get<City[]>(`${this.citiesUrl}`);
  }

  getCityById(id: number): Observable<City>{
    return this.http.get<City>(`${this.citiesUrl}/${id}`);
  }

  createCity(city: City): Observable<Object>{
    return this.http.post(`${this.citiesUrl}`, city);
  }

  deleteCity(id: number): Observable<Object>{
    return this.http.delete(`${this.citiesUrl}/${id}`);
  }
  
}                                           