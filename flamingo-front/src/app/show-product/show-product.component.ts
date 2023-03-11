import { Component, OnInit } from '@angular/core';
import { ProductViewModel } from '../models/product-view-model.module';
import { ProductService } from '../services/product.service';
import { MatPaginator } from '@angular/material/paginator';
import { ResponseViewModel } from '../models/Response-View-Model';

@Component({
  selector: 'app-show-product',
  templateUrl: './show-product.component.html',
  styleUrls: ['./show-product.component.scss']
})
export class ShowProductComponent implements OnInit {
productDetails : ResponseViewModel["data"];

displayedColumns: string[] = ['product Id', 'productName','image', 'description', 'quantity', 'price'];

  constructor(private productService :ProductService) { }

  ngOnInit(): void {
    this.getAllProducts();
  }

  public getAllProducts(){

    this.productService.getAllProducts().subscribe(
      (resp) => {
        console.log(resp);
        this.productDetails=resp.data;
      },
      (err:Error)=>{
        console.log(err);
      }
    )
  }

}
