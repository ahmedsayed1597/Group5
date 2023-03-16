import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { OrderOne } from '../models/Order-One.model';

@Injectable({
  providedIn: 'root'
})
export class OrderOneService {

  constructor(private _http:HttpClient) {   }

  public addOrder(orderOne:OrderOne,email:string){
    return this._http.post<OrderOne>("http://localhost:9090/api/public/users/"+email+"/orderOne",orderOne)
  }
}
