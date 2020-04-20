import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { AuthenticationService } from './authentication.service';
import { Observable } from 'rxjs';
import { Task } from '../model/Task';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  private baseUrl = 'http://localhost:8080/api/tasks';

  private httpHeaders = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + this.authService.getToken()
    })
  };

  constructor(private http: HttpClient, private authService: AuthenticationService) { }


  getTask(id: number): Observable<Object> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  createTask(task: Task): Observable<Task> {
    return this.http.post<Task>(`${this.baseUrl}` + `/create`, task, this.httpHeaders);
  }

  deleteTask(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/delete/${id}`, this.httpHeaders);
  }

  getMyTasksList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`, this.httpHeaders);
  }

  createReport(task: Task): Observable<Object> {
    return this.http.put(`${this.baseUrl}` + `/report`, task, this.httpHeaders);
  }

  removeTask(id: number): Observable<Object> {
    return this.http.put(`${this.baseUrl}` + `/remove/${id}`, id, this.httpHeaders);
  }

  acceptTask(id: number): Observable<Object> {
    return this.http.put(`${this.baseUrl}` + `/accept/${id}`, id, this.httpHeaders);
  }

  finishTask(id: number): Observable<Object> {
    return this.http.put(`${this.baseUrl}` + `/done/${id}`, id, this.httpHeaders);
  }

  getMySentList(): Observable<any> {
    return this.http.get(`${this.baseUrl}/sent`, this.httpHeaders);
  }

  correctTask(id: number): Observable<Object> {
    return this.http.put(`${this.baseUrl}` + `/correct/${id}`, id, this.httpHeaders);
  }

  removeInOwner(id: number): Observable<Object> {
    return this.http.put(`${this.baseUrl}` + `/remove-in-owner/${id}`, id, this.httpHeaders);
  }

}
