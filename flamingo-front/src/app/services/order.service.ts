import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private httpClient: HttpClient) { }
  userID:any = localStorage.getItem('sub');
  cartID:any = localStorage.getItem('CartId');
  url = "/public/users/{emailId}/carts/{cartId}/payments/{paymentMethod}/order"
  baseURL:any = "http://localhost:9090/api"

  post(){
    const token = localStorage.getItem('jwtToken');

    console.log(token);
    const head = new HttpHeaders();
    head.append(
      'Authorization', `Bearer ${token}`)
    console.log(this.userID)
    return this.httpClient.post(`${this.baseURL}/public/users/${this.userID}/carts/${this.cartID}/order`, "sss", {headers: head })
  }
}
