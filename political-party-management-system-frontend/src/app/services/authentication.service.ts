import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { User } from '../model/User';
import { Observable } from 'rxjs';
//import { map } from 'rxjs/operators';
import * as jwtDecode from 'jwt-decode';
import 'rxjs/add/operator/map';

interface UserData {
  username: string;
  token: string;
}

@Injectable()
export class AuthenticationService {

  readonly REGISTER_URL = 'http://localhost:8080/api/register';
  readonly LOGIN_URL = 'http://localhost:8080/api/auth';


  private httpHeaders = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) { }

  register(user: User): Observable<any> {
    return this.http.post(this.REGISTER_URL, user, this.httpHeaders);
  }

  login(user): Observable<any> {
    return this.http.post<any>(this.LOGIN_URL, user, this.httpHeaders)
      .map(tokenResp => {
        if (tokenResp && tokenResp.token) {
          console.log(tokenResp);
          localStorage.setItem('currentUser', JSON.stringify({ username: user.username, token: tokenResp.token }));
        }
      });
  }

  getLoggedUsername(): string {
    const currentUser = localStorage.getItem('currentUser');
    if (currentUser) {
      const currentUserObject: UserData = JSON.parse(currentUser);
      return currentUserObject.username;
    }
    return undefined;
  }

  getToken(): string {
    const currentUser = JSON.parse(localStorage.getItem('currentUser'));
    const token = currentUser && currentUser.token;
    return token ? token : '';
  }

  private getDecodedAccessToken(): Observable<any> {
    try {
      return jwtDecode(this.getToken());
    } catch (Error) {
      return null;
    }
  }

  getRolesArray(): any[] {
    let rolesArray: any[];
    if (this.isLoggedIn()) {
      rolesArray = this.getDecodedAccessToken()['roles'];
    }
    return rolesArray ? rolesArray : [];
  }


  logout() {
    localStorage.removeItem('currentUser');
  }

  isLoggedIn(): boolean {
    return this.getDecodedAccessToken() ? true : false;
  }

  isLoggedAdmin(): boolean {
    let status = false;
    this.getRolesArray().forEach(role => {
      if (role.authority === 'ROLE_ADMIN') {
        status = true;
      }
    });
    return status;
  }

  isLoggedPresident(): boolean {
    let status = false;
    this.getRolesArray().forEach(role => {
      if (role.authority === 'ROLE_PARTY_PRESIDENT') {
        status = true;
      }
    });
    return status;
  }

  isLoggedVicePresident(): boolean {
    let status = false;
    this.getRolesArray().forEach(role => {
      if (role.authority === 'ROLE_PARTY_VICE_PRESIDENT') {
        status = true;
      }
    });
    return status;
  }

  isLoggedSecretary(): boolean {
    let status = false;
    this.getRolesArray().forEach(role => {
      if (role.authority === 'ROLE_PARTY_SECRETARY') {
        status = true;
      }
    });
    return status;
  }

  isLoggedRegionPresident(): boolean {
    let status = false;
    this.getRolesArray().forEach(role => {
      if (role.authority === 'ROLE_REGION_PRESIDENT') {
        status = true;
      }
    });
    return status;
  }

  isLoggedRegionVicePresident(): boolean {
    let status = false;
    this.getRolesArray().forEach(role => {
      if (role.authority === 'ROLE_REGION_VICE_PRESIDENT') {
        status = true;
      }
    });
    return status;
  }

  isLoggedRegionSecretary(): boolean {
    let status = false;
    this.getRolesArray().forEach(role => {
      if (role.authority === 'ROLE_REGION_SECRETARY') {
        status = true;
      }
    });
    return status;
  }

  isLoggedConstituencyPresident(): boolean {
    let status = false;
    this.getRolesArray().forEach(role => {
      if (role.authority === 'ROLE_CONSTITUENCY_PRESIDENT') {
        status = true;
      }
    });
    return status;
  }

  isLoggedConstituencyVicePresident(): boolean {
    let status = false;
    this.getRolesArray().forEach(role => {
      if (role.authority === 'ROLE_CONSTITUENCY_VICE_PRESIDENT') {
        status = true;
      }
    });
    return status;
  }

  isLoggedConstituencySecretary(): boolean {
    let status = false;
    this.getRolesArray().forEach(role => {
      if (role.authority === 'ROLE_CONSTITUENCY_SECRETARY') {
        status = true;
      }
    });
    return status;
  }

  isLoggedUser(): boolean {
    let status = false;
    this.getRolesArray().forEach(role => {
      if (role.authority === 'ROLE_USER') {
        status = true;
      }
    });
    return status;
  }

  isLoggedMember(): boolean {
    let status = false;
    this.getRolesArray().forEach(role => {
      if (role.authority === 'ROLE_MEMBER') {
        status = true;
      }
    });
    return status;
  }

  reloadPage() {
    window.location.reload();
  }

}
