import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { AuthenticationService } from './authentication.service';
import { Observable } from 'rxjs';
import { User } from '../model/User';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = 'http://localhost:8080/api/users';

  //token przekazywany do metod działających na endpointach wymagających autoryzacji
  private httpHeaders = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + this.authService.getToken()
    })
  };

  constructor(private http: HttpClient, private authService: AuthenticationService) { }


  getUser(id: number): Observable<Object> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  getUserByUsername(username: string): Observable<User> {
    return this.http.get<User>(`${this.baseUrl}/username/${username}`, this.httpHeaders);
  }

  getUserByUsernameWithoutAdmin(username: string): Observable<User> {
    return this.http.get<User>(`${this.baseUrl}/user-name/${username}`, this.httpHeaders);
  }

  getUserByEmail(email: string): Observable<User> {
    return this.http.get<User>(`${this.baseUrl}/email/${email}`, this.httpHeaders);
  }

  createUser(user: User): Observable<User> {
    return this.http.post<User>(`${this.baseUrl}` + `/create`, user);
  }

  updateUser(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value, this.httpHeaders);
  }

  updateUserProperties(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/update/${id}`, value, this.httpHeaders);
  }

  updateUserFromPresident(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/update-from-president/${id}`, value, this.httpHeaders);
  }

  updateUserFromRegionPresident(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/update-from-region-president/${id}`, value, this.httpHeaders);
  }

  updateUserFromConstituencyPresident(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/update-from-constituency-president/${id}`, value, this.httpHeaders);
  }

  deleteUser(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, this.httpHeaders);
  }

  getMe(): Observable<any> {
    return this.http.get(`${this.baseUrl}/me`, this.httpHeaders);
  }

  getUsersList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`, this.httpHeaders);
  }

  getUsersForPresident(): Observable<any> {
    return this.http.get(`${this.baseUrl}/for-president`, this.httpHeaders);
  }

  getUsersForRegionPresident(): Observable<any> {
    return this.http.get(`${this.baseUrl}/for-region-president`, this.httpHeaders);
  }

  getUsersForConstituencyPresident(): Observable<any> {
    return this.http.get(`${this.baseUrl}/for-constituency-president`, this.httpHeaders);
  }

  getUserByName(name: string): Observable<User[]> {
    return this.http.get<User[]>(`${this.baseUrl}/name/${name}`, this.httpHeaders);
  }

  getUserBySurname(surname: string): Observable<User[]> {
    return this.http.get<User[]>(`${this.baseUrl}/surname/${surname}`, this.httpHeaders);
  }

  getUserByRegion(region: string): Observable<User[]> {
    return this.http.get<User[]>(`${this.baseUrl}/region/${region}`, this.httpHeaders);
  }

  getActiveUsers(): Observable<User[]> {
    return this.http.get<User[]>(`${this.baseUrl}/active`);
  }

  banUser(id: number): Observable<Object> {
    return this.http.put(`${this.baseUrl}/ban/${id}`, id, this.httpHeaders);
  }

  choosePartyVicePresident(id: number): Observable<Object> {
    return this.http.put(`${this.baseUrl}/party-vice-president/${id}`, id, this.httpHeaders);
  }

  choosePartySecretary(id: number): Observable<Object> {
    return this.http.put(`${this.baseUrl}/party-secretary/${id}`, id, this.httpHeaders);
  }

}
