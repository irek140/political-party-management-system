import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { AuthenticationService } from './authentication.service';
import { Observable } from 'rxjs';
import { JoinMessage } from '../model/JoinMessage';
import { User } from '../model/User';

@Injectable({
  providedIn: 'root'
})
export class JoinService {

  private baseUrl = 'http://localhost:8080/api/join';

  private httpHeaders = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + this.authService.getToken()
    })
  };

  constructor(private http: HttpClient, private authService: AuthenticationService) { }

  join(joinMessage: JoinMessage): Observable<JoinMessage> {
    return this.http.post<JoinMessage>(`${this.baseUrl}`, joinMessage, this.httpHeaders);
  }

  getRequestsList(): Observable<any> {
    return this.http.get(`${this.baseUrl}` + `/give-requests`);
  }

  hasJoinMessage(): Observable<any> {
    return this.http.get(`${this.baseUrl}` + `/has`);
  }

  getJoinMessageInfo(): Observable<User> {
    return this.http.get<User>(`${this.baseUrl}/user`, this.httpHeaders);
  }
}
