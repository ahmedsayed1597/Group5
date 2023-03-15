import { HttpClient } from '@angular/common/http';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { Cart, CartItem } from 'src/app/models/cart.model';
import { CartService } from 'src/app/services/cart.service';
import { loadStripe } from '@stripe/stripe-js';
import { Subscription } from 'rxjs';
import jwt_decode  from 'jwt-decode'
import { Router } from '@angular/router';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
})
export class CartComponent implements OnInit, OnDestroy {
  cart: Cart = { items: [] };
  displayedColumns: string[] = [
    'product',
    'name',
    'price',
    'quantity',
    'total',
    'action',
  ];
  dataSource: CartItem[] = [];
  cartSubscription: Subscription | undefined;

  constructor(private cartService: CartService, private http: HttpClient, private _CartService:CartService, private _Router:Router) {}

  ngOnInit(): void {
    this.cartSubscription = this.cartService.cart.subscribe((_cart: Cart) => {
      this.cart = _cart;
      this.dataSource = _cart.items;
    });
  }

  getTotal(items: CartItem[]): number {
    //this._CartService.setTotalPrice(cart.items)
    let total = 0;
    for(let i=0; i<items.length; i++){
      total = total + items[i].quantity * items[i].price
    }
    this._CartService.setTotalPrice(total)
    return this.cartService.getTotal(items);
  }

  onAddQuantity(item: CartItem): void {
    this.cartService.addToCart(item);
  }

  onRemoveFromCart(item: CartItem): void {
    this.cartService.removeFromCart(item);
  }

  onRemoveQuantity(item: CartItem): void {
    this.cartService.removeQuantity(item);
  }

  onClearCart(): void {
    this.cartService.clearCart();
  }

  onCheckout(): void {
    this.http
      .post('http://localhost:4242/checkout', {
        items: this.cart.items,
      })
      .subscribe(async (res: any) => {
        let stripe = await loadStripe('your token');
        stripe?.redirectToCheckout({
          sessionId: res.id,
        });
      });
  }

  ngOnDestroy() {
    if (this.cartSubscription) {
      this.cartSubscription.unsubscribe();
    }
  }

  checkOutCart(){
    let cart:any = localStorage.getItem('cart');
    cart = JSON.parse(cart)
    let total = 0;
    for(let i=0; i<cart.length; i++){
      this._CartService.sendCart(cart[i].id, cart[i].quantity).subscribe({
        next:(response:any) =>{
          // let token:any = localStorage.getItem('jwtToken')
          // let decode = jwt_decode(token)
          // console.log(decode)
          total = total + response.totalPrice
          localStorage.removeItem('cart')
          this._Router.navigate(['address'])
          this.onClearCart()
          console.log(total)
          this._CartService.totalPrice = total
        },error:(err) =>{console.log(err)}
      });
    }
    
    

  }
}
