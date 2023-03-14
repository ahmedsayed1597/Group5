import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { UserAuthService } from './services/user-auth.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  
  constructor(private _HttpClient: HttpClient, private _UserAuthService:UserAuthService) { }
  baseURL = "http://localhost:9090/api"

  signUp(userData: any): Observable<any> {
    return this._HttpClient.post(`${this.baseURL}/register`, userData);
  }

  signIn(userData: any, token:any): Observable<any> {
    let queryParams = new HttpParams();
    queryParams = queryParams.append("token",token);
    this._UserAuthService.setToken(token)
    return this._HttpClient.post(`${this.baseURL}/login`, userData, {params:queryParams});
  }

}
