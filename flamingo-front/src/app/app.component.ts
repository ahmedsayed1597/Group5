import { Component, OnInit } from '@angular/core';
import { Cart, CartItem } from './models/cart.model';
import { CartService } from './services/cart.service';
<<<<<<< HEAD
=======

>>>>>>> 56764417ce7a4ed01ea5cb60a15cd4688d574bb1

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
})
export class AppComponent implements OnInit {
  cart: Cart = { items: [] };

  constructor(private cartService: CartService) {}

  ngOnInit() {
    this.cartService.cart.subscribe((_cart) => {
      this.cart = _cart;
    });
  }
}
