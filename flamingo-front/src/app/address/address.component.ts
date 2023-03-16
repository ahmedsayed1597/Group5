import { Component, OnInit } from '@angular/core';
import { CartService } from '../services/cart.service';
import { OrderService } from '../services/order.service';



@Component({
  selector: 'app-address',
  templateUrl: './address.component.html',
  styleUrls: ['./address.component.scss']
})
export class AddressComponent implements OnInit {

  totalPrice:any
  constructor( public _CartService:CartService, private _OrderService:OrderService) { 
    this.setTotalPrice()
  }

  ngOnInit(): void {
    console.log(this._CartService.getTotalPrice())
  }

  getTotalPrice():number{
    return this._CartService.getTotalPrice();
  }

  setTotalPrice(){
     this.totalPrice = this._CartService.totalPrice;
    return this._CartService.totalPrice
  }

  submitOrder(){
    this._OrderService.post().subscribe({
      next: (response) => {
        console.log(response)
      },error:((err) => {console.log(err)})
    })
  }
}
