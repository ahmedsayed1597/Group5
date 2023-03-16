import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProductToAdd } from '../models/ProductToAdd.model';
import { ProductService } from '../services/product.service';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.scss']
})
export class ProductDetailsComponent implements OnInit {

  productId:string;
  product:ProductToAdd;
  constructor(private _router:ActivatedRoute,
    private productService:ProductService) { }

  ngOnInit(): void {
    this._router.paramMap.subscribe(
      param=>{this.productId =param.get('id')||'';}

    )
    this.getProduct();

  }

  getProduct(){

    this.productService.getProductById(+this.productId).subscribe(
      (resp)=>{this.product=resp;
        console.log(this.product);}
      ,(err)=>{console.log(err);});


  }





}
