import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Article } from '../model/Article';
import { AuthenticationService } from './authentication.service';

@Injectable({
  providedIn: 'root'
})
export class ArticleService {

  private baseUrl = 'http://localhost:8080/api/articles';

  private httpHeaders = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + this.authService.getToken()
    })
  };

  constructor(private http: HttpClient, private authService: AuthenticationService) { }

  getArticle(id: number): Observable<Object> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  createArticle(article: Article): Observable<Article> {
    return this.http.post<Article>(`${this.baseUrl}` + `/create`, article, this.httpHeaders);
  }

  updateArticle(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  deleteArticle(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/delete/${id}`, this.httpHeaders);
  }

  getArticlesList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }

  getUnacceptableArticlesList(): Observable<any> {
    return this.http.get(`${this.baseUrl}/unacceptable`, this.httpHeaders);
  }

  acceptArticle(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/accept/${id}`, value, this.httpHeaders);
  }


  /*
  deleteAll(): Observable<any> {
    return this.http.delete(`${this.baseUrl}` + `/delete`, { responseType: 'text'});
  }
*/

}
