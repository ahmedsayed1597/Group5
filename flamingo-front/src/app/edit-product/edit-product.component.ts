import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CategoryView } from '../models/Category-view.model';
import { ProductToAdd } from '../models/ProductToAdd.model';
import { ProductService } from '../services/product.service';
import { StoreService } from '../services/store.service';

@Component({
  selector: 'app-edit-product',
  templateUrl: './edit-product.component.html',
  styleUrls: ['./edit-product.component.scss']
})
export class EditProductComponent implements OnInit {
  categories: CategoryView[]=[];
  selectedFile: File;
  product=new ProductToAdd();
  id:string;

  //bind with changing the image
  onFileSelected(event:any){
    this.selectedFile=event.target.files[0];
    console.log(this.selectedFile)
  }

    build(productName: string ,Description: string ,Price: number ,
      Quantity: number   ){
        this.product.productName=productName;
        this.product.description =Description;
        this.product.price =Price;
        this.product.quantity =Quantity;
        console.log(this.product);
    }
  constructor(private activateRoute:ActivatedRoute,
    private productService:ProductService
            ) { }

  ngOnInit(): void {
    this.activateRoute.paramMap.subscribe(
      param=>{this.id =param.get('productId')||'';}
    );
    this.getById(+this.id);
    // this.getCategories();
  }


  public edit(){
    console.log(this.product);
    this.productService.editProduct(+this.id,this.product).subscribe((resp)=>console.log(resp.productName))
  }

  public getById(id:number){

    return this.productService.getProductById(id).subscribe(
      (resp)=>this.product=resp
    )
  }



  // getCategories(){
  //    this._StoreService.getAllCategories()
  //   .subscribe((response)=>this.categories=response.data );
  // }


}
