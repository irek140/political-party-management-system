import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { AuthenticationService } from './authentication.service';
import { Survey2 } from '../model/surveys/survey2';
import { Observable } from 'rxjs';
import { Survey3 } from '../model/surveys/Survey3';
import { Survey4 } from '../model/surveys/Survey4';
import { Survey5 } from '../model/surveys/Survey5';
import { Survey6 } from '../model/surveys/Survey6';
import { Survey7 } from '../model/surveys/Survey7';

@Injectable({
  providedIn: 'root'
})
export class SurveyService {

  private baseUrl = 'http://localhost:8080/api';

  private httpHeaders = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + this.authService.getToken()
    })
  };

  constructor(private http: HttpClient, private authService: AuthenticationService) { }



  // Ankieta z dwiema odpowiedziami
  createSurvey2(survey2: Survey2): Observable<Survey2> {
    return this.http.post<Survey2>(`${this.baseUrl}` + `/survey2/create`, survey2, this.httpHeaders);
  }

  createSurvey2ForMembers(survey2: Survey2): Observable<Survey2> {
    return this.http.post<Survey2>(`${this.baseUrl}` + `/survey2/create-for-members`, survey2, this.httpHeaders);
  }

  getSurveys2ToVote(): Observable<any> {
    return this.http.get(`${this.baseUrl}` + `/survey2/list-to-vote`, this.httpHeaders);
  }

  getSurveys2WithResults(): Observable<any> {
    return this.http.get(`${this.baseUrl}` + `/survey2/list-with-results`, this.httpHeaders);
  }

  vote1(id: number): Observable<Object> {
    return this.http.put(`${this.baseUrl}/survey2/vote1/${id}`, id, this.httpHeaders);
  }

  vote2(id: number): Observable<Object> {
    return this.http.put(`${this.baseUrl}/survey2/vote2/${id}`, id, this.httpHeaders);
  }

  deleteSurveys2(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/survey2/delete/${id}`, this.httpHeaders);
  }

  generateSurveys2Raport(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}` + `/survey2/generate-raport/${id}`, this.httpHeaders);
  }



  // Ankieta z trzema odpowiedziami
  createSurvey3(survey3: Survey3): Observable<Survey3> {
    return this.http.post<Survey3>(`${this.baseUrl}` + `/survey3/create`, survey3, this.httpHeaders);
  }

  createSurvey3ForMembers(survey3: Survey3): Observable<Survey3> {
    return this.http.post<Survey3>(`${this.baseUrl}` + `/survey3/create-for-members`, survey3, this.httpHeaders);
  }

  getSurveys3ToVote(): Observable<any> {
    return this.http.get(`${this.baseUrl}` + `/survey3/list-to-vote`, this.httpHeaders);
  }

  getSurveys3WithResults(): Observable<any> {
    return this.http.get(`${this.baseUrl}` + `/survey3/list-with-results`, this.httpHeaders);
  }

  vote1Survey3(id: number): Observable<Object> {
    return this.http.put(`${this.baseUrl}/survey3/vote1/${id}`, id, this.httpHeaders);
  }

  vote2Survey3(id: number): Observable<Object> {
    return this.http.put(`${this.baseUrl}/survey3/vote2/${id}`, id, this.httpHeaders);
  }

  vote3Survey3(id: number): Observable<Object> {
    return this.http.put(`${this.baseUrl}/survey3/vote3/${id}`, id, this.httpHeaders);
  }

  deleteSurveys3(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/survey3/delete/${id}`, this.httpHeaders);
  }

  generateSurveys3Raport(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}` + `/survey3/generate-raport/${id}`, this.httpHeaders);
  }



  // Ankieta z czterema odpowiedziami
  createSurvey4(survey4: Survey4): Observable<Survey4> {
    return this.http.post<Survey4>(`${this.baseUrl}` + `/survey4/create`, survey4, this.httpHeaders);
  }

  createSurvey4ForMembers(survey4: Survey4): Observable<Survey4> {
    return this.http.post<Survey4>(`${this.baseUrl}` + `/survey4/create-for-members`, survey4, this.httpHeaders);
  }

  getSurveys4ToVote(): Observable<any> {
    return this.http.get(`${this.baseUrl}` + `/survey4/list-to-vote`, this.httpHeaders);
  }

  getSurveys4WithResults(): Observable<any> {
    return this.http.get(`${this.baseUrl}` + `/survey4/list-with-results`, this.httpHeaders);
  }

  vote1Survey4(id: number): Observable<Object> {
    return this.http.put(`${this.baseUrl}/survey4/vote1/${id}`, id, this.httpHeaders);
  }

  vote2Survey4(id: number): Observable<Object> {
    return this.http.put(`${this.baseUrl}/survey4/vote2/${id}`, id, this.httpHeaders);
  }

  vote3Survey4(id: number): Observable<Object> {
    return this.http.put(`${this.baseUrl}/survey4/vote3/${id}`, id, this.httpHeaders);
  }

  vote4Survey4(id: number): Observable<Object> {
    return this.http.put(`${this.baseUrl}/survey4/vote4/${id}`, id, this.httpHeaders);
  }

  deleteSurveys4(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/survey4/delete/${id}`, this.httpHeaders);
  }

  generateSurveys4Raport(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}` + `/survey4/generate-raport/${id}`, this.httpHeaders);
  }



  // Ankieta z pięcioma odpowiedziami
  createSurvey5(survey5: Survey5): Observable<Survey5> {
    return this.http.post<Survey5>(`${this.baseUrl}` + `/survey5/create`, survey5, this.httpHeaders);
  }

  createSurvey5ForMembers(survey5: Survey5): Observable<Survey5> {
    return this.http.post<Survey5>(`${this.baseUrl}` + `/survey5/create-for-members`, survey5, this.httpHeaders);
  }

  getSurveys5ToVote(): Observable<any> {
    return this.http.get(`${this.baseUrl}` + `/survey5/list-to-vote`, this.httpHeaders);
  }

  getSurveys5WithResults(): Observable<any> {
    return this.http.get(`${this.baseUrl}` + `/survey5/list-with-results`, this.httpHeaders);
  }

  vote1Survey5(id: number): Observable<Object> {
    return this.http.put(`${this.baseUrl}/survey5/vote1/${id}`, id, this.httpHeaders);
  }

  vote2Survey5(id: number): Observable<Object> {
    return this.http.put(`${this.baseUrl}/survey5/vote2/${id}`, id, this.httpHeaders);
  }

  vote3Survey5(id: number): Observable<Object> {
    return this.http.put(`${this.baseUrl}/survey5/vote3/${id}`, id, this.httpHeaders);
  }

  vote4Survey5(id: number): Observable<Object> {
    return this.http.put(`${this.baseUrl}/survey5/vote4/${id}`, id, this.httpHeaders);
  }

  vote5Survey5(id: number): Observable<Object> {
    return this.http.put(`${this.baseUrl}/survey5/vote5/${id}`, id, this.httpHeaders);
  }

  deleteSurveys5(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/survey5/delete/${id}`, this.httpHeaders);
  }

  generateSurveys5Raport(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}` + `/survey5/generate-raport/${id}`, this.httpHeaders);
  }



  // Ankieta z sześcioma odpowiedziami
  createSurvey6(survey6: Survey6): Observable<Survey6> {
    return this.http.post<Survey6>(`${this.baseUrl}` + `/survey6/create`, survey6, this.httpHeaders);
  }

  createSurvey6ForMembers(survey6: Survey6): Observable<Survey6> {
    return this.http.post<Survey6>(`${this.baseUrl}` + `/survey6/create-for-members`, survey6, this.httpHeaders);
  }

  getSurveys6ToVote(): Observable<any> {
    return this.http.get(`${this.baseUrl}` + `/survey6/list-to-vote`, this.httpHeaders);
  }

  getSurveys6WithResults(): Observable<any> {
    return this.http.get(`${this.baseUrl}` + `/survey6/list-with-results`, this.httpHeaders);
  }

  vote1Survey6(id: number): Observable<Object> {
    return this.http.put(`${this.baseUrl}/survey6/vote1/${id}`, id, this.httpHeaders);
  }

  vote2Survey6(id: number): Observable<Object> {
    return this.http.put(`${this.baseUrl}/survey6/vote2/${id}`, id, this.httpHeaders);
  }

  vote3Survey6(id: number): Observable<Object> {
    return this.http.put(`${this.baseUrl}/survey6/vote3/${id}`, id, this.httpHeaders);
  }

  vote4Survey6(id: number): Observable<Object> {
    return this.http.put(`${this.baseUrl}/survey6/vote4/${id}`, id, this.httpHeaders);
  }

  vote5Survey6(id: number): Observable<Object> {
    return this.http.put(`${this.baseUrl}/survey6/vote5/${id}`, id, this.httpHeaders);
  }

  vote6Survey6(id: number): Observable<Object> {
    return this.http.put(`${this.baseUrl}/survey6/vote6/${id}`, id, this.httpHeaders);
  }

  deleteSurveys6(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/survey6/delete/${id}`, this.httpHeaders);
  }

  generateSurveys6Raport(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}` + `/survey6/generate-raport/${id}`, this.httpHeaders);
  }



  // Ankieta z siedmioma odpowiedziami
  createSurvey7(survey7: Survey7): Observable<Survey7> {
    return this.http.post<Survey7>(`${this.baseUrl}` + `/survey7/create`, survey7, this.httpHeaders);
  }

  createSurvey7ForMembers(survey7: Survey7): Observable<Survey7> {
    return this.http.post<Survey7>(`${this.baseUrl}` + `/survey7/create-for-members`, survey7, this.httpHeaders);
  }

  getSurveys7ToVote(): Observable<any> {
    return this.http.get(`${this.baseUrl}` + `/survey7/list-to-vote`, this.httpHeaders);
  }

  getSurveys7WithResults(): Observable<any> {
    return this.http.get(`${this.baseUrl}` + `/survey7/list-with-results`, this.httpHeaders);
  }

  vote1Survey7(id: number): Observable<Object> {
    return this.http.put(`${this.baseUrl}/survey7/vote1/${id}`, id, this.httpHeaders);
  }

  vote2Survey7(id: number): Observable<Object> {
    return this.http.put(`${this.baseUrl}/survey7/vote2/${id}`, id, this.httpHeaders);
  }

  vote3Survey7(id: number): Observable<Object> {
    return this.http.put(`${this.baseUrl}/survey7/vote3/${id}`, id, this.httpHeaders);
  }

  vote4Survey7(id: number): Observable<Object> {
    return this.http.put(`${this.baseUrl}/survey7/vote4/${id}`, id, this.httpHeaders);
  }

  vote5Survey7(id: number): Observable<Object> {
    return this.http.put(`${this.baseUrl}/survey7/vote5/${id}`, id, this.httpHeaders);
  }

  vote6Survey7(id: number): Observable<Object> {
    return this.http.put(`${this.baseUrl}/survey7/vote6/${id}`, id, this.httpHeaders);
  }

  vote7Survey7(id: number): Observable<Object> {
    return this.http.put(`${this.baseUrl}/survey7/vote7/${id}`, id, this.httpHeaders);
  }

  deleteSurveys7(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/survey7/delete/${id}`, this.httpHeaders);
  }

  generateSurveys7Raport(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}` + `/survey7/generate-raport/${id}`, this.httpHeaders);
  }
}
