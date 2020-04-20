import { Injectable } from '@angular/core';
import { AuthenticationService } from './authentication.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Region } from '../model/Region';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RegionService {

  private baseUrl = 'http://localhost:8080/api/region';

  private httpHeaders = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + this.authService.getToken()
    })
  };

  constructor(private http: HttpClient, private authService: AuthenticationService) { }

  createRegion(regionName: string): Observable<Region> {
    return this.http.post<Region>(`${this.baseUrl}` + `/create`, regionName, this.httpHeaders);
  }

  deleteRegion(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/delete/${id}`, this.httpHeaders);
  }

  updateRegion(id: number, region: Region): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, region, this.httpHeaders);
  }

  getRegionList(): Observable<any> {
    return this.http.get(`${this.baseUrl}/list`, this.httpHeaders);
  }
}
