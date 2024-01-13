import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Exam } from 'src/app/models/exam.model';

@Injectable({
  providedIn: 'root'
})

export class ExamService {
  
  private examsUrl = "http://localhost:8080/api/exams";

  constructor(private http:HttpClient) { }

  getExamList(): Observable<Exam[]>{
    return this.http.get<Exam[]>(`${this.examsUrl}`);
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