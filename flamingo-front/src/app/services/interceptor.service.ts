
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { finalize, Observable } from 'rxjs';
import { LoaderService } from './loader.service';

@Injectable({
  providedIn: 'root'
})

export class InterceptorService implements HttpInterceptor {

  constructor(private _LoaderService:LoaderService) { }

  intercept(req:HttpRequest<any>, next:HttpHandler):Observable<HttpEvent<any>>{

    // const authHeader = 'Bearer ' + localStorage.getItem('jwtToken');
    // const authReq = req.clone({ headers: req.headers.set('Authorization', authHeader) });
    //  return next.handle(authReq);
    
    this._LoaderService.isLoading.next(true);
    return next.handle(req).pipe(
      finalize(
        ()=>{
          this._LoaderService.isLoading.next(false);
        }
      )
    )
  }
}
