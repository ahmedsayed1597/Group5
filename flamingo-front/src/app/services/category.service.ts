import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CategoryView } from '../models/Category-view.model';
import { CategoryToAdd } from '../models/CategoryToAdd.model';
import { ResponseViewModel } from '../models/Response-View-Model';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private _http:HttpClient) { }

  public getCategories(){
    return this._http.get<ResponseViewModel>("http://localhost:9090/api/public/categories?pageSize=100")
    

  }

  public addCategory(categoryToAdd:CategoryToAdd ){

    return this._http.post<CategoryView>("http://localhost:9090/api/admin/categories",categoryToAdd);
  }
}
