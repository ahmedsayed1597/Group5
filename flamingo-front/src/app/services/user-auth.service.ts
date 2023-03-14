import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserAuthService {

  constructor() { }
  isLogin = new BehaviorSubject<boolean>(false);
  
  public setToken(JWTtoken:string){
    localStorage.setItem("token" , JWTtoken);
  }

  public getToken(){
    return localStorage.getItem("token");
  }

  public clear(){
    localStorage.clear();
  }

  public isLoggedIn(){
    if(this.getToken() !== null) return true;
    return false
  }
}
