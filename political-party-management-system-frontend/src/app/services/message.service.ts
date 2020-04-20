import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { AuthenticationService } from './authentication.service';
import { Observable } from 'rxjs';
import { Message } from '../model/Message';

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  private baseUrl = 'http://localhost:8080/api/messages';

  private httpHeaders = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + this.authService.getToken()
    })
  };

  constructor(private http: HttpClient, private authService: AuthenticationService) { }


  getMessage(id: number): Observable<Object> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  createMessage(message: Message): Observable<Message> {
    return this.http.post<Message>(`${this.baseUrl}` + `/create`, message, this.httpHeaders);
  }

  deleteMessage(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/delete/${id}`, this.httpHeaders);
  }

  getMessagesList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`, this.httpHeaders);
  }
}
