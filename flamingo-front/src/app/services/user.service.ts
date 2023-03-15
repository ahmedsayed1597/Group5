import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private _httpClient:HttpClient) { }

  public getAllUsers(){

    this._httpClient.get("http://localhost:9090/admin/users")
  }

}

