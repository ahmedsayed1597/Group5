import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Product } from 'src/app/models/product.model';
import { ProductToAdd } from 'src/app/models/ProductToAdd.model';
import { CartService } from 'src/app/services/cart.service';
import { ProductService } from 'src/app/services/product.service';
import { StoreService } from 'src/app/services/store.service';
import { UserAuthService } from 'src/app/services/user-auth.service';

const ROWS_HEIGHT: { [id: number]: number } = { 1: 400, 3: 335, 4: 350 };

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit, OnDestroy {
  cols = 3;
  rowHeight: number = ROWS_HEIGHT[this.cols];
  products: Array<ProductToAdd> | undefined;
  count = 0;
  sort = 'desc';
  field='price';
  category: any =1;
  pageSize:number=6;
  pageNumber:number=0;
  productsSubscription: Subscription | undefined;

  constructor(
    private cartService: CartService,
    private storeService: StoreService,
    private _UserAuthService:UserAuthService,
    private productService:ProductService
  ) {}

  ngOnInit(): void {
    // this.getProducts();
    this.getProductsPaged();
  }

  onColumnsCountChange(colsNum: number): void {
    this.cols = colsNum;
    this.rowHeight = ROWS_HEIGHT[colsNum];
  }

  nextPage(): void {
    this.count++
    this.getProductsPaged();
  }

  previousPage(): void {
    this.count--
    this.getProductsPaged();
  }

  onSortChange(newSort: string): void {
    this.sort = newSort;
    this.getProducts();
  }

  onShowCategory(newCategory: string): void {
    this.count = 0
    this.category = newCategory;
    this.getProducts();
  }


  getProducts(): void {
    console.log(this.category)
    this.productsSubscription = this.storeService

      .getAllProducts(+this.category, this.sort, this.count,this.field)
      .subscribe((_products:any) => {
        this.products = _products.data;
      });
  }

  getProductsFromAllCategories(): void {
    console.log(this.category)
    this.productsSubscription = this.storeService

      .getAllProductsFromAllCategory()
      .subscribe((_products:any) => {
        this.products = _products.data;
        console.log(_products.data)
      });
  }

  searchByKeyword(keyword:string){
    console.log(keyword);
    this.pageNumber=0;
    if(keyword != ''){
    this.productService.searchProductsByKeyword(keyword
      ,this.pageNumber,this.pageSize,this.field,this.sort)
      .subscribe((resp)=>{this.products=resp.data;
      })
    }else
    this.getProductsPaged();
  }




  getProductsPaged(){
    this.productsSubscription = this.storeService

    .getAllProductsFromAllCategorySortedAndPaged(this.sort,this.count,this.field,this.pageSize)
    .subscribe((_products:any) => {
      this.products = _products.data;
      console.log(_products.data)
    });
  }
  onAddToCart(product: any): void {
    this.cartService.addToCart({
      product: product.image,
      name: product.productName,
      price: product.price,
      quantity: 1,
      id: product.productId,
    });
  }

  ngOnDestroy(): void {
    if (this.productsSubscription) {
      this.productsSubscription.unsubscribe();
    }
  }

  ifUserAdmin(){
    if(this._UserAuthService.getRoles() == 'ADMIN'){
      return true;
    }return false
  }
}
