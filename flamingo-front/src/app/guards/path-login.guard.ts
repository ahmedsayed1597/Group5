import { Injectable } from '@angular/core';
import { CanActivate, Route, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { UserAuthService } from '../services/user-auth.service';

@Injectable({
  providedIn: 'root'
})
export class PathLoginGuard implements CanActivate {
  constructor(private _UserAuthService:UserAuthService, private _Router:Router){}

  canActivate(): boolean {
    if(this._UserAuthService.getToken() == null){
      return true
    }
    this._Router.navigate(['home'])
    return false;
  }
  
}
