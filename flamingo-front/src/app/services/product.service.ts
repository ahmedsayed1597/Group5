import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
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
    const formData = new FormData();
    formData.append('product',new Blob( [JSON.stringify(product)],{type:'application/json'}));
    formData.append('image', image, image.name);

    return this._httpClient.post<ProductToAdd>
            ('http://localhost:9090/api/admin/categories/'+categoryId+'/product', formData);

  }

  public getAllProducts(){

    return this._httpClient.get<ResponseViewModel>("http://localhost:9090/api/public/products?pageSize=1000")
  }
}

