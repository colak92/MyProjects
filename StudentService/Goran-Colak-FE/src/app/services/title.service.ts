import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Title } from '../models/title.model';

@Injectable({
  providedIn: 'root'
})

export class TitleService {
  
  private titlesUrl = "http://localhost:8080/api/titles";

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