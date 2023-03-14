import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { UserAuthService } from './services/user-auth.service';
import jwt_decode from 'jwt-decode';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(
    private _HttpClient: HttpClient,
    private _UserAuthService: UserAuthService
  ) {}
  baseURL = 'http://localhost:9090/api';

  signUp(userData: any): Observable<any> {
    return this._HttpClient.post(`${this.baseURL}/register`, userData);
  }

  signIn(userData: any): Observable<any> {
    return this._HttpClient.post(`${this.baseURL}/login`, userData);
  }

  decodeToken(token: string): any {
    try {
      const decodedToken = jwt_decode(token);
      return decodedToken;
    } catch (error) {
      console.log(error);
      return null;
    }
  }


  getClaimFromToken(token: string, claimName: string): any {
    try {
      const decodedToken:any = jwt_decode(token);
      return decodedToken[claimName];
    } catch (error) {
      console.log(error);
      return null;
    }
  }
}
