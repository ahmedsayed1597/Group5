import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AddressRequest } from '../models/Address.model';
import { UserRequest } from '../models/UserRequest.model';

@Injectable({
  providedIn: 'root'
})
export class AddressService {

  baseUrl:string="http://localhost:9090/api/public/users/";
  constructor(private _http:HttpClient) { }

  public addAddress(userId:number,address:AddressRequest){
    return this._http.post<UserRequest>(this.baseUrl+userId+"/address",address);
  }

  public updateAddress(userId:number,address:AddressRequest){
    return this._http.post<UserRequest>(this.baseUrl+userId+"/address",address);
  }

  public getAddress(userId:number,address:AddressRequest){
    return this._http.get<UserRequest>(this.baseUrl+userId+"/address");
  }

  public deleteAddress(userId:number,address:AddressRequest){
    return this._http.delete<UserRequest>(this.baseUrl+userId+"/address");
  }
}
