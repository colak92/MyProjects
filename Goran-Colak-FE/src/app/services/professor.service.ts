import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Professor } from 'src/app/models/professor.model';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})

export class ProfessorService {
  
  private apiUrl = environment.apiUrl;
  private professorsUrl = this.apiUrl + "/professors";
  private professorsListUrl = this.apiUrl + "/professors-list";

  constructor(private http:HttpClient) { }

  getProfessorList(): Observable<Professor[]>{
    return this.http.get<Professor[]>(`${this.professorsListUrl}`);
  }

  getProfessorListForSubject(id: number): Observable<Professor[]>{
    return this.http.get<Professor[]>(`${this.professorsListUrl}/${id}`);
  }

  getPageAndSortList(params: any): Observable<any> {
    return this.http.get<any>(this.professorsUrl, { params });
  }

  getProfessorById(id: number): Observable<Professor>{
    return this.http.get<Professor>(`${this.professorsUrl}/${id}`);
  }

  createProfessor(professor: Professor): Observable<Object>{
    return this.http.post(`${this.professorsUrl}`, professor);
  }

  updateProfessor(id: number, professor: Professor): Observable<Object>{
    return this.http.put(`${this.professorsUrl}/${id}`, professor);
  }

  deleteProfessor(id: number): Observable<Object>{
    return this.http.delete(`${this.professorsUrl}/${id}`);
  }
  
}                                           