import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import {  Observable } from 'rxjs';

import { UserAuthService } from './user-auth.service';
@Injectable()
export class JwtInterceptor implements HttpInterceptor {
  constructor(private localstorageToken: UserAuthService) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    const token = this.localstorageToken.getToken();
    const isAPI = request.url.startsWith("http://localhost:9090/api/public/users/:userId/carts");

    if (token && isAPI) {
      request = request.clone({
        headers: request.headers.set('Authorization', `Bearer ${token}`)
      });
    }
    console.log(request )
    return next.handle(request);
  }
}
