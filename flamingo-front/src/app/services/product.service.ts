import { HttpClient, HttpHeaders } from '@angular/common/http';
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
      `http://localhost:9090/api/products?pageNumber=${pageNumber}&pageSize=${pageSize}`
      )
  }

  public editProduct(productId:number,editedProduct :ProductToAdd){
    return this._httpClient.put<ProductViewModel>("http://localhost:9090/api/products/"+productId,editedProduct)

  }

  public getProductById(id:number){
    return this._httpClient.get<ProductToAdd>("http://localhost:9090/api/products/"+id);
  }


  public deleteProduct(id:number){
    return this._httpClient.delete<string>("http://localhost:9090/api/admin/products/"+id);
  }

  public addCategory(categoryToAdd:CategoryToAdd ){

    const token = localStorage.getItem('jwtToken');

    console.log(token);
    const head = new HttpHeaders();
    head.append(
      'Authorization', `Bearer ${token}`)
    return this._httpClient.post<CategoryView>("http://localhost:9090/api/admin/categories",categoryToAdd,{headers:head});
  }


}

