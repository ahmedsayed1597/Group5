import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Product } from 'src/app/models/product.model';
import { ProductToAdd } from 'src/app/models/ProductToAdd.model';
import { ResponseViewModel } from 'src/app/models/Response-View-Model';

@Component({
  selector: '[app-product-box]',
  templateUrl: './product-box.component.html',
})
export class ProductBoxComponent {
  @Input() fullWidthMode = false;
  @Input() product: ProductToAdd | undefined;
  @Output() addToCart = new EventEmitter();

  constructor() {}

  onAddToCart(): void {
    this.addToCart.emit(this.product);
  }
}
