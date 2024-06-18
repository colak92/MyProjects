import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Student } from 'src/app/models/student.model';

@Injectable({
  providedIn: 'root'
})

export class StudentService {
  
  private studentsUrl = "http://localhost:8080/api/students";
  private studentsListUrl = "http://localhost:8080/api/students-list";

  constructor(private http:HttpClient) { }

  getStudentList(): Observable<Student[]>{
    return this.http.get<Student[]>(`${this.studentsListUrl}`);
  }

  getPageAndSortList(params: any): Observable<any> {
    return this.http.get<any>(this.studentsUrl, { params });
  }

  getStudentById(id: number): Observable<Student>{
    return this.http.get<Student>(`${this.studentsUrl}/${id}`);
  }

  createStudent(student: Student): Observable<Object>{
    return this.http.post(`${this.studentsUrl}`, student);
  }

  updateStudent(id: number, student: Student): Observable<Object>{
    return this.http.put(`${this.studentsUrl}/${id}`, student);
  }

  deleteStudent(id: number): Observable<Object>{
    return this.http.delete(`${this.studentsUrl}/${id}`);
  }
  
}                                           