import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import jwt_decode from 'jwt-decode';
import { tick } from '@angular/core/testing';

@Injectable({
  providedIn: 'root'
})
export class UserAuthService {

  constructor() { }
  isLogin = new BehaviorSubject<boolean>(false);

  public setToken(JWTtoken:string){
    localStorage.setItem("jwtToken" , JWTtoken);
  }

  public getToken(){
    return localStorage.getItem("jwtToken");
  }


  public clear(){
    localStorage.clear();
  }

  public isLoggedIn(){
    if(this.getToken() !== null) return true;
    return false
  }
  public setRoles(token: string){

    let role = this.getClaimFromToken(token,"Role_");
    localStorage.setItem("Role_",role)
  }

  public setUserID(token: string){
    let userID = this.getClaimFromToken(token,"UserId");
    localStorage.setItem("UserId",userID)
  }

  public setCartID(token: string){
    let cartID = this.getClaimFromToken(token,"CartId");
    localStorage.setItem("CartId",cartID)
  }


  public getRoles(){
    return localStorage.getItem('Role_') ;
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
