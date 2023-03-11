import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ProductToAdd } from '../models/ProductToAdd.model';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  addedProduct:ProductToAdd
  constructor(private _httpClient:HttpClient) { }

  public addProduct(categoryId:number,product:ProductToAdd){
    return this._httpClient.post<ProductToAdd>
            ('http://localhost:9090/api/admin/categories/'+categoryId+'/product', product);

  }
}
