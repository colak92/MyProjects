import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ExamPeriod } from '../models/examperiod.model';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})

export class ExamPeriodService {
  
  private apiUrl = environment.apiUrl;
  private examperiodsUrl = this.apiUrl + "/examperiods";
  private examperiodsActiveUrl = this.apiUrl + "/examperiods-active";

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