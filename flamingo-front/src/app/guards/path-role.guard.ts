import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { UserAuthService } from '../services/user-auth.service';

@Injectable({
  providedIn: 'root'
})
export class PathRoleGuard implements CanActivate {
  constructor(private _UserAuthService:UserAuthService, private _Router:Router){}
  canActivate(): boolean {
    if(this._UserAuthService.getRoles() == 'ADMIN'){
      return true
    }
    this._Router.navigate(['home'])
    return false;
  }
  
}
