import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { AuthenticationService } from './authentication.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class JoinMessageService {

  private baseUrl = 'http://localhost:8080/api/join';

  private httpHeaders = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + this.authService.getToken()
    })
  };

  constructor(private http: HttpClient, private authService: AuthenticationService) { }


  getJoinMessageList(): Observable<any> {
    return this.http.get(`${this.baseUrl}` + `/give-requests`, this.httpHeaders);
  }

  becomeMember(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}` + `/member/${id}`, value, this.httpHeaders);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/delete/${id}`, this.httpHeaders);
  }

}
