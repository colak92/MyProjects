import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Title } from '../models/title.model';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})

export class TitleService {
  
  private apiUrl = environment.apiUrl;
  private titlesUrl = this.apiUrl + "/titles";

  constructor(private http:HttpClient) { }

  getTitleList(): Observable<Title[]> {
    return this.http.get<Title[]>(`${this.titlesUrl}`);
  }

  getTitleById(id: number): Observable<Title>{
    return this.http.get<Title>(`${this.titlesUrl}/${id}`);
  }

  createTitle(title: Title): Observable<Object>{
    return this.http.post(`${this.titlesUrl}`, title);
  }

  deleteTitle(id: number): Observable<Object>{
    return this.http.delete(`${this.titlesUrl}/${id}`);
  }
  
}  