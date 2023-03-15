import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';


// const STORE_BASE_URL = 'https://fakestoreapi.com';
const STORE_BASE_URL = 'http://localhost:9090/api'

@Injectable({
  providedIn: 'root',
})
export class StoreService {
  constructor(private httpClient: HttpClient) {}

  // getAllProducts(
  //   limit = '12',
  //   sort = 'desc',
  //   category?: string
  // ): Observable<Array<Product>> {
  //   return this.httpClient.get<Array<Product>>(
  //     `${STORE_BASE_URL}/products${
  //       category ? '/category/' + category : ''
  //     }?sort=${sort}&limit=${limit}`
  //   );
  // }

  public getAllProducts(categoryId:number, sort:string, pageNumber:number,field :string){
    let queryParams = new HttpParams();
    queryParams = queryParams.append("sortOrder",sort);
    queryParams = queryParams.append("pageNumber",pageNumber);
    queryParams = queryParams.append("sortBy",field);

    return this.httpClient.get(`${STORE_BASE_URL}/categories/${categoryId}/products`, {params:queryParams})
  }

  public getAllProductsFromAllCategory(){

    return this.httpClient.get(`${STORE_BASE_URL}/products`)
  }

  getAllCategories(): Observable<any> {
    return this.httpClient.get(
      `${STORE_BASE_URL}/categories?pageSize=1000`
    );
  }

}
