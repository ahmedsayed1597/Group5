import { Component, Input } from '@angular/core';
import { Route, Router } from '@angular/router';
import { Cart, CartItem } from 'src/app/models/cart.model';
import { CartService } from 'src/app/services/cart.service';
import { UserAuthService } from 'src/app/services/user-auth.service';
import { UserService } from 'src/app/user.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
  private _cart: Cart = { items: [] };
  itemsQuantity = 0;

  @Input()
  get cart(): Cart {
    return this._cart;
  }

  set cart(cart: Cart) {
    this._cart = cart;

    this.itemsQuantity = cart.items
      .map((item) => item.quantity)
      .reduce((prev, curent) => prev + curent, 0);
  }


  constructor(private cartService: CartService, private _Router:Router, public _UserAuthService:UserAuthService) {

  }
  
  getTotal(items: CartItem[]): number {
    return this.cartService.getTotal(items);
  }

  onClearCart(): void {
    this.cartService.clearCart();
  }

  routeToRegister(){
    this._Router.navigate(['Register'])
  }

  // logOut() {
  //   this._UserService.logOut();
  //   this._Router.navigate(['Login'])
  //   this.userLogin = null;

  // }

  logOut(){
    this._UserAuthService.clear();
  }
}
