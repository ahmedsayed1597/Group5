import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ProductViewModel } from '../models/product-view-model.module';
import { ProductToAdd } from '../models/ProductToAdd.model';
import { ResponseViewModel } from '../models/Response-View-Model';

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

  public getAllProducts(){

    return this._httpClient.get<ResponseViewModel>("http://localhost:9090/api/public/products")
  }
}
