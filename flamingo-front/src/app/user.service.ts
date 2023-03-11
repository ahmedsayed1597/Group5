import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private _HttpClient: HttpClient) { }

  signUp(userData: any): Observable<any> {
    return this._HttpClient.post('http://localhost:9090/flamingo/Register', userData);
  }

  signIn(userData: any): Observable<any> {
    return this._HttpClient.post('http://localhost:9090/flamingo/Login', userData,{headers: {'Content-Type':'application/json; charset=UTF-8'}});
  }
}
