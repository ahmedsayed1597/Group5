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
  productDetails : ResponseViewModel["data"];

  page: number = 1;
  count: number = 0;
  tableSizes: any = [5, 10, 25, 50];

  pageNumber:number=0;
  field:string= "productId";
  orderBy:string = "asc";
  pageSize: number = 7;

  response:any;
  displayedColumns: string[] = ['product Id', 'productName','image', 'description', 'quantity', 'price'];

  constructor(private productService :ProductService) { }

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

  onTableDataChange(event: any) {
    this.page = event;
    this.getAllProducts();
  }
  onTableSizeChange(event: any): void {
    this.pageSize = event.target.value;
    this.page = 1;
    this.getAllProducts();
  }



}
