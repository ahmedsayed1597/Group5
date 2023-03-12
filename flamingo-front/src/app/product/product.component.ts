import { Component, OnInit } from '@angular/core';
<<<<<<< HEAD
=======
import { NgForm } from '@angular/forms';
import { DomSanitizer } from '@angular/platform-browser';
import { CategoryView } from '../models/Category-view.model';
import { FileHandle } from '../models/file-handle-model';
import { ProductToAdd } from '../models/ProductToAdd.model';
import { ResponseViewModel } from '../models/Response-View-Model';
import { CategoryService } from '../services/category.service';
import { ProductService } from '../services/product.service';
>>>>>>> 56764417ce7a4ed01ea5cb60a15cd4688d574bb1

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.scss']
})
export class ProductComponent implements OnInit {

<<<<<<< HEAD
  constructor() { }

  ngOnInit(): void {
  }

}
=======
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
            ,private sanitizer:DomSanitizer
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

>>>>>>> 56764417ce7a4ed01ea5cb60a15cd4688d574bb1
