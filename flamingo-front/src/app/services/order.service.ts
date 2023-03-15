import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private httpClient: HttpClient) { }
  userID:any = parseInt(localStorage.getItem('UserId')!);
  cartID:any = localStorage.getItem('CartId');
  url = "/public/users/{emailId}/carts/{cartId}/payments/{paymentMethod}/order"
  baseURL:any = "http://localhost:9090"

  post(){
    return this.httpClient.post(`${this.baseURL}/public/users/${this.userID}/carts/${this.cartID}/payments/cash/order`, "sss")
  }
}
