import { Component, EventEmitter, Input, Output } from '@angular/core';

import { ProductToAdd } from 'src/app/models/ProductToAdd.model';
import { UserAuthService } from 'src/app/services/user-auth.service';


@Component({
  selector: '[app-product-box]',
  templateUrl: './product-box.component.html',
})
export class ProductBoxComponent {
  @Input() fullWidthMode = false;
  @Input() product: ProductToAdd | undefined;
  @Output() addToCart = new EventEmitter();

  constructor(public _UserAuthService:UserAuthService) {}

  onAddToCart(): void {
    this.addToCart.emit(this.product);
  }

  ifUserAdmin(){
    if(this._UserAuthService.getRoles() == 'ADMIN'){
      return true;
    }return false
  }
  
}
