import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-admin-navbar',
  templateUrl: './admin-navbar.component.html',
  styleUrls: ['./admin-navbar.component.scss'],
})
export class AdminNavbarComponent implements OnInit {
  navbaritems: any = [
    {
      label: 'Add Product or Category',
      link: '/admin/add',
    },
    {
      label: 'Show Category',
      link: '/admin/showCategory',
    },
    {
      label: 'Edit Category',
      link: '/admin/editCategory/:categoryId',
    },
    {
      label: 'Show Product',
      link: '/admin/showProduct',
    },
    {
      label: 'Edit Product',
      link: 'admin/editProduct/:productId',
    },
  ];
  constructor() {}

  ngOnInit(): void {}
}
