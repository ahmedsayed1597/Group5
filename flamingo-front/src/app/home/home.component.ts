import { Component, OnInit } from '@angular/core';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  products: any;

  constructor(private ProductService: ProductService) { }

  ngOnInit(): void {
    this.getAllProduct()
  }

  getAllProduct() {
    this.ProductService.getAllProduct().subscribe({
      next: (response) => {

        this.products = response;
        console.log(this.products)

      }
      ,
      error: (err) => { console.log(err); }
    });
  }
}

