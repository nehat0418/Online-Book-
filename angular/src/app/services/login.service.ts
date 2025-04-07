import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private apiUrl = "http://localhost:8088/api/users"; 

  constructor(private http: HttpClient) { }

  createUser(user: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/api/users`, user);
  }

  getUser(userId: string): Observable<any> {
    return this.http.get(`${this.apiUrl}/api/users/get?userId=${userId}`);
  }

  getAllUsers(): Observable<any> {
    return this.http.get(`${this.apiUrl}/api/users`);
  }

  updateUser(user: any): Observable<any> {
    return this.http.put(`${this.apiUrl}/api/users`, user);
  }

  login(email: string, password: string) {
    return this.http.post(`${this.apiUrl}/login`, {
      email,
      password
    });
  }
}
