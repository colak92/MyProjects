import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Exam } from 'src/app/models/exam.model';
import { environment } from '../../environments/environment';
import { Student } from '../models/student.model';

@Injectable({
  providedIn: 'root'
})

export class ExamService {

  private apiUrl = environment.apiUrl;
  private examsUrl = this.apiUrl + "/exams";
  private examProfessorUrl = this.apiUrl + "/exams/professor";
  private examRegUrl = this.apiUrl + "/exams/exam-reg";

  constructor(private http:HttpClient) { }

  getExamList(): Observable<Exam[]>{
    return this.http.get<Exam[]>(`${this.examsUrl}`);
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

  registerExams(student: Student, examList: Exam[]): Observable<string> {
    const studentStr = JSON.stringify(student);
    const examListStr = JSON.stringify(examList);

    let params = new HttpParams()
      .set('student', studentStr)
      .set('examList', examListStr);

    return this.http.get<string>(this.examRegUrl, { params });
  }
  
}                                           