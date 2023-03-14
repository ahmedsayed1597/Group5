import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CategoryView } from '../models/Category-view.model';
import { CategoryToAdd } from '../models/CategoryToAdd.model';
import { ProductViewModel } from '../models/product-view-model';
import { ProductToAdd } from '../models/ProductToAdd.model';
import { ResponseViewModel } from '../models/Response-View-Model';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  addedProduct:ProductToAdd
  constructor(private _httpClient:HttpClient) { }

  public addProduct(categoryId:number,product: any, image: File){
    console.log(product)
    const formData = new FormData();
    formData.append('product',new Blob( [JSON.stringify(product)],{type:'application/json'}));
    formData.append('image', image, image.name);
// console.log(JSON.stringify(formData))
    return this._httpClient.post<ProductToAdd>
            (`http://localhost:9090/api/admin/categories/${categoryId}/product`, formData);

  }

  // public getAllProducts(){

  //   return this._httpClient.get<ResponseViewModel>("http://localhost:9090/api/public/products")
  // }

  public getAllProducts(pageNumber:number , pageSize:number , field:string, orderBy:string){

    return this._httpClient.get<ResponseViewModel>(
      `http://localhost:9090/api/products?pageNumber=${pageNumber}&
      pageSize=${pageSize}&field=${field}&orderBy=${orderBy}`
      )
  }

  public addCategory(categoryToAdd:CategoryToAdd ){

    return this._httpClient.post<CategoryView>("http://localhost:9090/api/admin/categories",categoryToAdd);
  }
}

