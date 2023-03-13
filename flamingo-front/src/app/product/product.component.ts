import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { CategoryView } from '../models/Category-view.model';
import { ProductToAdd } from '../models/ProductToAdd.model';
import { CategoryService } from '../services/category.service';
import { ProductService } from '../services/product.service';

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
            ,private categoryService : CategoryService) { }

  ngOnInit(): void {
    this.getCategories();
  }

  addProduct(product: any, image: File){
    this.productService.addProduct(this.product.categoryId,this.product,this.selectedFile).
      subscribe(
        (product:ProductToAdd) => {console.log(product);
        },
        (error:Error) => {console.log(error)}
          );
  }

  getCategories(){
     this.categoryService.getCategories()
    .subscribe((response)=>this.categories=response.data );
  }

}


