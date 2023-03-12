import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Product } from 'src/app/models/product.model';
import { ProductToAdd } from 'src/app/models/ProductToAdd.model';
import { CartService } from 'src/app/services/cart.service';
import { StoreService } from 'src/app/services/store.service';

const ROWS_HEIGHT: { [id: number]: number } = { 1: 400, 3: 335, 4: 350 };

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
})
export class HomeComponent implements OnInit, OnDestroy {
  cols = 3;
  rowHeight: number = ROWS_HEIGHT[this.cols];
  products: Array<ProductToAdd> | undefined;
  count = 0;
  sort = 'desc';
  category: any =1;
  productsSubscription: Subscription | undefined;

  constructor(
    private cartService: CartService,
    private storeService: StoreService
  ) {}

  ngOnInit(): void {
    // this.getProducts();
    this.getProductsFromAllCategories();
  }

  onColumnsCountChange(colsNum: number): void {
    this.cols = colsNum;
    this.rowHeight = ROWS_HEIGHT[colsNum];
  }

  nextPage(): void {
    this.count++
    this.getProducts();
  }

  previousPage(): void {
    this.count--
    this.getProducts();
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
    
      .getAllProducts(this.category, this.sort, this.count)
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
      });
  }

  onAddToCart(product: Product): void {
    this.cartService.addToCart({
      product: product.image,
      name: product.title,
      price: product.price,
      quantity: 1,
      id: product.id,
    });
  }

  ngOnDestroy(): void {
    if (this.productsSubscription) {
      this.productsSubscription.unsubscribe();
    }
  }
}
