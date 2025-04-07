import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BookCatalogService {
  private apiUrl = "http://localhost:8080/books"; 

  constructor(private http: HttpClient) { }

  getBooks(): Observable<any> {
    return this.http.get(`${this.apiUrl}`);
  }

  createBook(bookData: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/create`, bookData);
  }


  updateBook(bookId: string, bookData: any): Observable<any> {
    return this.http.put(`${this.apiUrl}/${bookId}`, bookData);
  }

  
}