import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ExamPeriod } from '../models/examperiod.model';

@Injectable({
  providedIn: 'root'
})

export class ExamPeriodService {
  
  private examperiodsUrl = "http://localhost:8080/api/examperiods";
  private examperiodsActiveUrl = "http://localhost:8080/api/examperiods-active";

  constructor(private http:HttpClient) { }

  getActiveExamPeriodList(): Observable<ExamPeriod[]> {
    return this.http.get<ExamPeriod[]>(`${this.examperiodsActiveUrl}`);
  }

  getExamPeriodList(): Observable<ExamPeriod[]> {
    return this.http.get<ExamPeriod[]>(`${this.examperiodsUrl}`);
  }

  getExamPeriodById(id: number): Observable<ExamPeriod>{
    return this.http.get<ExamPeriod>(`${this.examperiodsUrl}/${id}`);
  }

  createExamPeriod(examperiod: ExamPeriod): Observable<Object>{
    return this.http.post(`${this.examperiodsUrl}`, examperiod);
  }

  updateExamPeriod(id: number, examperiod: ExamPeriod): Observable<Object>{
    return this.http.put(`${this.examperiodsUrl}/${id}`, examperiod);
  }

  deleteExamPeriod(id: number): Observable<Object>{
    return this.http.delete(`${this.examperiodsUrl}/${id}`);
  }
  
}                                           