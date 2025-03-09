import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ExamReg } from '../models/examreg.model';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})

export class ExamRegService {
  
  private apiUrl = environment.apiUrl;
  private examregUrl = this.apiUrl + "/examregs";
  private examRegStudentUrl =  this.apiUrl + "/examregs/student";

  constructor(private http:HttpClient) { }

  getExamRegList(): Observable<ExamReg[]> {
    return this.http.get<ExamReg[]>(`${this.examregUrl}`);
  }

  getStudentExamRegList(id: number): Observable<ExamReg[]>{
    return this.http.get<ExamReg[]>(`${this.examRegStudentUrl}/${id}`);
  }

  getExamRegById(id: number): Observable<ExamReg>{
    return this.http.get<ExamReg>(`${this.examregUrl}/${id}`);
  }

  createExamReg(examreg: ExamReg): Observable<Object>{
    return this.http.post(`${this.examregUrl}`, examreg);
  }

  enterExamGrade(id: number, examreg: ExamReg): Observable<Object>{
    return this.http.put(`${this.examregUrl}/${id}`, examreg);
  }

  deleteExamReg(id: number): Observable<Object>{
    return this.http.delete(`${this.examregUrl}/${id}`);
  }

}                                           