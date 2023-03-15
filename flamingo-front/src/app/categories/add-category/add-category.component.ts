import { Component, OnInit } from '@angular/core';
import { CategoryToAdd } from '../../models/CategoryToAdd.model';
import { ProductService } from '../../services/product.service';

@Component({
  selector: 'app-add-category',
  templateUrl: './add-category.component.html',
  styleUrls: ['./add-category.component.scss']
})
export class AddCategoryComponent implements OnInit {

  category=new CategoryToAdd();
  categoryName:string;
  constructor(private categoryService:ProductService) { }

  ngOnInit(): void {
  }


  clearFields(){
    this.categoryName="";

  }
  addCategory(){

    console.log("add category");
    this.category.categoryName=this.categoryName;
    this.categoryService.addCategory(this.category)
    .subscribe((resp)=>console.log(resp)
    ,(err)=>console.log(err));
  }

}
