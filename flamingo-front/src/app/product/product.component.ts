import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { CategoryView } from '../models/Category-view.model';
import { ProductToAdd } from '../models/ProductToAdd.model';

import { ProductService } from '../services/product.service';
import { StoreService } from '../services/store.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.scss']
})
export class ProductComponent implements OnInit {

  categories: CategoryView[]=[];
  selectedFile: File;
  product=new ProductToAdd();

  //bind with changing the image
  onFileSelected(event:any){
    this.selectedFile=event.target.files[0];
    console.log(this.selectedFile)
  }

    build(productName: string ,Description: string ,Price: number ,
      Quantity: number  ,selectedOption: number ){
        this.product.productName=productName;
        this.product.description =Description;
        this.product.price =Price;
        this.product.quantity =Quantity;
        this.product.categoryId =selectedOption;
        console.log(this.product);
        this.addProduct(this.product,this.selectedFile)
    }
  constructor(private productService:ProductService
            ,private _StoreService : StoreService) { }

  ngOnInit(): void {
    this.getCategories();
  }

  addProduct(product: any, image: File){
    console.log(JSON.stringify(product))
    this.productService.addProduct(product.categoryId,product,image).
      subscribe(
        (product:ProductToAdd) => {console.log(product);
        },
        (error:Error) => {console.log(error)}
          );
  }

  getCategories(){
     this._StoreService.getAllCategories()
    .subscribe((response)=>this.categories=response.data );
  }

}


