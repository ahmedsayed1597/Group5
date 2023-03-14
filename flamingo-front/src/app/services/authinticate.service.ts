import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthinticateService {

  API="http://localhost:9090"
  requestHeader = new HttpHeaders(
    {"No-Auth":"True"}
  );
  constructor(private _http:HttpClient) { }

  
}
