import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { DomSanitizer } from '@angular/platform-browser';
import { FileHandle } from '../models/file-handle-model';
import { ProductToAdd } from '../models/ProductToAdd.model';
import { ProductService } from '../services/product.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.scss']
})
export class ProductComponent implements OnInit {

  product:ProductToAdd  ={
    productName:"asd",
    description:"qweqw",
    price:4786,
    quantity:65415,
    categoryId:0,
    image:""
    }
  constructor(private productService:ProductService
            ,private sanitizer:DomSanitizer) { }

  ngOnInit(): void {
  }

  addProduct(productForm :NgForm){
    this.productService.addProduct(this.product.categoryId,this.product).
      subscribe(
        (product:ProductToAdd) => {console.log(product);
        },
        (error:Error) => {console.log(error)}
          );
  }

  onFileSelected(event: any){
    console.log(event);
    if(event.target.files){
      const file = event.target.files[0];
      const fileHandle:FileHandle={
        file:file,
        url:this.sanitizer.bypassSecurityTrustUrl(
          window.URL.createObjectURL(file)
        )
      }
    }

  }

}

