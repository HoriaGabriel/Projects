import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Book } from '../models/book.models';
import { Reservation } from '../models/reservation.model';


const API_URL = 'http://localhost:8080/api/test/';
const API_URL2 = 'http://localhost:8080/api/test/librarian/books';
const API_URL3 = 'http://localhost:8080/api/test/add-book';
const API_URL4 = 'http://localhost:8080/api/test/customer/reserve';
const API_URL5 = 'http://localhost:8080/api/test/reservations';
const API_URL6 = 'http://localhost:8080/api/test/return-book';
const API_URL7 = 'http://localhost:8080/api/test/newreservations';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(private http: HttpClient) { }

  getPublicContent(): Observable<any> {
    return this.http.get(API_URL + 'all', { responseType: 'text' });
  }

  getAll(): Observable<Book[]> {
    return this.http.get<Book[]>(API_URL + 'customer');
  }

  getAll2(): Observable<Book[]> {
    return this.http.get<Book[]>(API_URL + 'librarian');
  }

  getAll3(data: any): Observable<Reservation[]> {
    return this.http.get<Reservation[]>(`${API_URL5}?username=${data}`);
  }

  getAll4(data: any): Observable<Reservation[]> {
    return this.http.get<Reservation[]>(`${API_URL7}?username=${data}`);
  }

  findByTitle(title: any): Observable<Book[]> {
    return this.http.get<Book[]>(`${API_URL + 'customer'}?title=${title}`);
  }

  findByTitle2(title: any): Observable<Book[]> {
    return this.http.get<Book[]>(`${API_URL + 'librarian'}?title=${title}`);
  }

  get(id: any): Observable<Book> {
    return this.http.get(`${API_URL2}/${id}`);
  }

  get2(id: any): Observable<Book> {
    return this.http.get(`${API_URL4}/${id}`);
  }

  update(id: any, data: any): Observable<any> {
    return this.http.put(`${API_URL2}/${id}`, data);
  }

  updateRes(data: any): Observable<any> {
    return this.http.put(`${API_URL6}`, data);
  }

  delete(id: any): Observable<any> {
    return this.http.delete(`${API_URL2}/${id}`);
  }

  create(data: any): Observable<any> {
    return this.http.post(API_URL3, data);
  }

  createRes(id:any, user: any, book: any): Observable<any> {
    return this.http.post(`${API_URL4}/${id}`,{user,book});
  }
}
