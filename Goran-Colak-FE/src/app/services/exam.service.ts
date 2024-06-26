import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Exam } from 'src/app/models/exam.model';

@Injectable({
  providedIn: 'root'
})

export class ExamService {
  
  private examsUrl = "http://localhost:8080/api/exams";

  private examStudentUrl = "http://localhost:8080/api/exams/student";

  private examProfessorUrl = "http://localhost:8080/api/exams/professor";

  constructor(private http:HttpClient) { }

  getExamList(): Observable<Exam[]>{
    return this.http.get<Exam[]>(`${this.examsUrl}`);
  }

  getStudentExamList(id: number): Observable<Exam[]>{
    return this.http.get<Exam[]>(`${this.examStudentUrl}/${id}`);
  }

  getProfessorExamList(id: number): Observable<Exam[]>{
    return this.http.get<Exam[]>(`${this.examProfessorUrl}/${id}`);
  }

  getExamById(id: number): Observable<Exam>{
    return this.http.get<Exam>(`${this.examsUrl}/${id}`);
  }

  createExam(exam: Exam): Observable<Object>{
    return this.http.post(`${this.examsUrl}`, exam);
  }

  updateExam(id: number, exam: Exam): Observable<Object>{
    return this.http.put(`${this.examsUrl}/${id}`, exam);
  }

  deleteExam(id: number): Observable<Object>{
    return this.http.delete(`${this.examsUrl}/${id}`);
  }
  
}                                           