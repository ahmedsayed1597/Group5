import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private _HttpClient: HttpClient) { }

  signUp(userData: any): Observable<any> {
    return this._HttpClient.post('https://api.storerestapi.com/auth/register', userData,{headers: {'Content-Type':'application/json; charset=UTF-8'}});
  }

  signIn(userData: any): Observable<any> {
    return this._HttpClient.post('https://api.storerestapi.com/auth/login', userData,{headers: {'Content-Type':'application/json; charset=UTF-8'}});
  }
}
