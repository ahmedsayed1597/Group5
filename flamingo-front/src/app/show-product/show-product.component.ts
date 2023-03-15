import { Component, OnInit } from '@angular/core';
import { ProductViewModel } from '../models/product-view-model';
import { ProductService } from '../services/product.service';
import { MatPaginator } from '@angular/material/paginator';
import { ResponseViewModel } from '../models/Response-View-Model';

@Component({
  selector: 'app-show-product',
  templateUrl: './show-product.component.html',
  styleUrls: ['./show-product.component.scss']
})
export class ShowProductComponent implements OnInit {
  constructor(private productService :ProductService) { }

productDetails : ResponseViewModel["data"];

displayedColumns:any=['product Id','productName','image','description','quantity','price','edt','delete'];
  pageNumber:number=0;
  field:string= "productId";
  orderBy:string = "asc";
  pageSize: number = 100;
  response:any;




  ngOnInit(): void {
    this.getAllProducts();
  }

  public getAllProducts(){

    this.productService.getAllProducts(this.pageNumber,this.pageSize,this.field,this.orderBy).subscribe(
      (resp) => {
        console.log(resp);
        this.productDetails=resp.data;
        this.response=resp;
      },
      (err:Error)=>{
        console.log(err);
      }
    )
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
