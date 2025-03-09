import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Subject } from 'src/app/models/subject.model';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})

export class SubjectService {
  
  private apiUrl = environment.apiUrl;
  private subjectsUrl = this.apiUrl + "/subjects";
  private subjectsListUrl = this.apiUrl + "/subjects-list";

  constructor(private http:HttpClient) { }

  getSubjectList(): Observable<Subject[]>{
    return this.http.get<Subject[]>(`${this.subjectsListUrl}`);
  }

  getSubjectListForProfessor(id: number): Observable<Subject[]>{
    return this.http.get<Subject[]>(`${this.subjectsListUrl}/${id}`);
  }
  
  getPageAndSortList(params: any): Observable<any> {
    return this.http.get<any>(this.subjectsUrl, { params });
  }

  getSubjectById(id: number): Observable<Subject>{
    return this.http.get<Subject>(`${this.subjectsUrl}/${id}`);
  }

  createSubject(subject: Subject): Observable<Object>{
    return this.http.post(`${this.subjectsUrl}`, subject);
  }

  updateSubject(id: number, subject: Subject): Observable<Object>{
    return this.http.put(`${this.subjectsUrl}/${id}`, subject);
  }

  deleteSubject(id: number): Observable<Object>{
    return this.http.delete(`${this.subjectsUrl}/${id}`);
  }
  
}                                           