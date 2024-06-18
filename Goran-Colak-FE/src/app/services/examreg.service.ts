import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ExamReg } from '../models/examreg.model';

@Injectable({
  providedIn: 'root'
})

export class ExamRegService {
  
  private examregUrl = "http://localhost:8080/api/examregs";

  constructor(private http:HttpClient) { }

  getExamRegList(): Observable<ExamReg[]> {
    return this.http.get<ExamReg[]>(`${this.examregUrl}`);
  }

  getExamRegById(id: number): Observable<ExamReg>{
    return this.http.get<ExamReg>(`${this.examregUrl}/${id}`);
  }

  saveExamReg(id: number, examreg: ExamReg): Observable<Object>{
    return this.http.put(`${this.examregUrl}/${id}`, examreg);
  }

  deleteExamReg(id: number): Observable<Object>{
    return this.http.delete(`${this.examregUrl}/${id}`);
  }
  
}                                           