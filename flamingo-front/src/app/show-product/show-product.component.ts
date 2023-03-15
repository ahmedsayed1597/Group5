import { Component, OnInit } from '@angular/core';
import { ProductViewModel } from '../models/product-view-model';
import { ProductService } from '../services/product.service';
import { MatPaginator } from '@angular/material/paginator';
import { ResponseViewModel } from '../models/Response-View-Model';
import { ProductToAdd } from '../models/ProductToAdd.model';
import { StoreService } from '../services/store.service';

@Component({
  selector: 'app-show-product',
  templateUrl: './show-product.component.html',
  styleUrls: ['./show-product.component.scss']
})

export class ShowProductComponent implements OnInit {
  constructor(private storeService:StoreService,private productService :ProductService) { }

productDetails : ResponseViewModel["data"];

displayedColumns:any=['product Id','productName','image','description','quantity','price','edt','delete'];
  pageNumber:number=0;
  field:string= "productId";
  orderBy:string = "asc";
  pageSize: number = 5;
  response:any;
  products: Array<ProductToAdd> | undefined;

  showLoadButton=true;


  ngOnInit(): void {
    this.getAllProducts();
  }

  public getAllProducts(){

    this.productService.getAllProducts(this.pageNumber,this.pageSize,this.field,this.orderBy).subscribe(
      (resp) => {
        console.log(resp);
        if(resp.lastPage){
        this.showLoadButton=false;
        }
        else
        this.showLoadButton=true;

        this.productDetails=resp.data;
        this.response=resp;
      },
      (err:Error)=>{
        console.log(err);
      }
    )
  }

  searchByKeyword(keyword:string){
    console.log(keyword);
    this.pageNumber=0;
    if(keyword != ''){
    this.productService.searchProductsByKeyword(keyword
      ,this.pageNumber,this.pageSize,this.field,this.orderBy)
      .subscribe((resp)=>{this.productDetails=resp.data;
      })
    }else
    this.getAllProducts();
  }


  loadNext(){
    this.pageNumber++;
    this.getAllProducts();
  }

  loadBefore(){
    if(this.pageNumber>0)
    this.pageNumber--;
    this.getAllProducts();
  }

  public deleteProduct(id:number){
    console.log(id);
    this.productService.deleteProduct(id).subscribe(
      (resp)=>
      {this.getAllProducts();
      console.log("succecful");},
      (err)=>{
        console.log(err);
      }
    )
  }



}
