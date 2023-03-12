import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CategoryView } from '../models/Category-view.model';
import { ResponseViewModel } from '../models/Response-View-Model';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private _http:HttpClient) { }

  public getCategories(){
    return this._http.get<ResponseViewModel>("http://localhost:9090/api/public/categories?pageSize=100")
    

  }
}
