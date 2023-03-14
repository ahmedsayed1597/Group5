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
    return this._http.get<ResponseViewModel>("http://localhost:9090/api/categories?pageSize=150")


  }

  public addCategory(categoryToAdd:CategoryToAdd ){

    return this._http.post<CategoryView>("http://localhost:9090/api/admin/categories",categoryToAdd);
  }

  public updateCategory(categoryToAdd:CategoryToAdd,categoryId:number){
    return this._http.put<CategoryView>("http://localhost:9090/api/admin/categories/"+categoryId,categoryToAdd);

  }
  public deleteCategory(categoryId:number){
    return this._http.delete<String>("http://localhost:9090/api/admin/categories/"+categoryId);

  }

  public getCategoryById(categoryId:number){
    return this._http.get<CategoryView>("http://localhost:9090/api/categories/"+categoryId)

  }
}
