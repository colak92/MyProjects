import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

const AUTH_API = 'http://localhost:8080/api/auth/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(private http: HttpClient) { }

  login(username: string, password: string): Observable<any> {
    return this.http.post(AUTH_API + 'signin', {
      username,
      password
    }, httpOptions);
  }

  registerAsProfessor(username: string, email: string, password: string, roleName: string,
    professorFirstName: string, professorLastName: string, reelectionDate: Date, titleName: string): Observable<any> {
    return this.http.post(AUTH_API + 'signup', {
      username,
      email,
      password,
      roleName,
      professorFirstName,
      professorLastName,
      reelectionDate,
      titleName
    }, httpOptions);
  }

  registerAsStudent(username: string, email: string, password: string, roleName: string,
    indexNumber: string, indexYear: number, studentFirstName: string, studentLastName: string, currentYearoFStudy: number): Observable<any> {
    return this.http.post(AUTH_API + 'signup', {
      username,
      email,
      password,
      roleName,
      indexNumber,
      indexYear,
      studentFirstName,
      studentLastName,
      currentYearoFStudy
    }, httpOptions);
  }

}
