import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { AuthenticationService } from './authentication.service';
import { Constituency } from '../model/Constituency';
import { Observable } from 'rxjs';
import { Region } from '../model/Region';

@Injectable({
  providedIn: 'root'
})
export class ConstituencyService {

  private baseUrl = 'http://localhost:8080/api/constituency';

  private httpHeaders = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + this.authService.getToken()
    })
  };

  constructor(private http: HttpClient, private authService: AuthenticationService) { }

  createConstituency(region: Region, constituencyName: string): Observable<Constituency> {
    return this.http.post<Constituency>(`${this.baseUrl}` + `/create/${constituencyName}`, region, this.httpHeaders);
  }

  getConstituencyByRegion(region: string): Observable<Constituency[]> {
    return this.http.get<Constituency[]>(`${this.baseUrl}/list/by-region/${region}`, this.httpHeaders);
  }

  getAllConstituency(): Observable<Constituency[]> {
    return this.http.get<Constituency[]>(`${this.baseUrl}/list`, this.httpHeaders);
  }

  deleteConstituency(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/delete/${id}`, this.httpHeaders);
  }

  updateConstituency(id: number, value: Constituency): Observable<Object> {
    return this.http.put(`${this.baseUrl}/update/${id}`, value, this.httpHeaders);
  }

}
