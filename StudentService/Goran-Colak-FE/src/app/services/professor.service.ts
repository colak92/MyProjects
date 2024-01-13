import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Professor } from 'src/app/models/professor.model';

@Injectable({
  providedIn: 'root'
})

export class ProfessorService {
  
  private professorsUrl = "http://localhost:8080/api/professors";
  private professorsListUrl = "http://localhost:8080/api/professors-list";

  constructor(private http:HttpClient) { }

  getProfessorList(): Observable<Professor[]>{
    return this.http.get<Professor[]>(`${this.professorsListUrl}`);
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