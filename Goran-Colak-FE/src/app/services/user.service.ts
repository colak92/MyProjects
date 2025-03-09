import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})

export class UserService {

  private apiUrl = environment.apiUrl;
  private apiTestUrl = this.apiUrl + "/test/";
  private profileUrl = this.apiUrl + "/profile";

  constructor(private http: HttpClient) { }

  getPublicContent(): Observable<any> {
    return this.http.get(this.apiTestUrl + 'all', { responseType: 'text' });
  }

  getStudentBoard(): Observable<any> {
    return this.http.get(this.apiTestUrl + 'student', { responseType: 'text' });
  }

  getProfessorBoard(): Observable<any> {
    return this.http.get(this.apiTestUrl + 'professor', { responseType: 'text' });
  }

  getAdminBoard(): Observable<any> {
    return this.http.get(this.apiTestUrl + 'admin', { responseType: 'text' });
  }

  getProfileById(id: number): Observable<any>{
    return this.http.get<any>(`${this.profileUrl}/${id}`);
  }

  updateProfile(id: number, user: any): Observable<any>{
    return this.http.put(`${this.profileUrl}/${id}`, user);
  }
}
