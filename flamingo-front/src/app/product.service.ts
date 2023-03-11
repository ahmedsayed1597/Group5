import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private _HttpClient: HttpClient) { }

  getAllProduct(): Observable<any>{
    return this._HttpClient.get('https://api.storerestapi.com/products');
  }
}
