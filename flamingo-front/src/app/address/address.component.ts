import { Component, OnInit } from '@angular/core';
import { CartService } from '../services/cart.service';



@Component({
  selector: 'app-address',
  templateUrl: './address.component.html',
  styleUrls: ['./address.component.scss']
})
export class AddressComponent implements OnInit {

  constructor( public _CartService:CartService) { }

  ngOnInit(): void {
    console.log(this._CartService.getTotalPrice())
  }

  getTotalPrice():number{
    return this._CartService.getTotalPrice();
  }

}
