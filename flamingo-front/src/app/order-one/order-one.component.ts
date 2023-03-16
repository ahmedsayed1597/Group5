import { Token } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { OrderOne } from '../models/Order-One.model';
import { OrderItem } from '../models/OrderItem.model';
import { OrderOneService } from '../services/order-one.service';
import { UserAuthService } from '../services/user-auth.service';

@Component({
  selector: 'app-order-one',
  templateUrl: './order-one.component.html',
  styleUrls: ['./order-one.component.scss']
})
export class OrderOneComponent implements OnInit {

  orderOne = new OrderOne();
  orderItem = new OrderItem();
  constructor(private orderOneService:OrderOneService
    ,private userAuth:UserAuthService) { }

  ngOnInit(): void {
  }

  public build(){
this.orderOne.orderItem=this.orderItem;
    console.log(JSON.stringify(this.orderOne))
    this.buy();
    
  }

  public buy(){
    let token = localStorage.getItem('jwtToken');
    let email = this.userAuth.getClaimFromToken(token!,"email");
    this.orderOneService.addOrder(this.orderOne,email);
  }
}
