import { Component, OnInit } from '@angular/core';
import { CategoryToAdd } from 'src/app/models/CategoryToAdd.model';
import { CategoryService } from 'src/app/services/category.service';

@Component({
  selector: 'app-add-category',
  templateUrl: './add-category.component.html',
  styleUrls: ['./add-category.component.scss']
})
export class AddCategoryComponent implements OnInit {

  category=new CategoryToAdd();
  CategotyName:string;
  constructor(private categoryService:CategoryService) { }

  ngOnInit(): void {
  }

  addCategory(){

    this.category.categoryName=this.CategotyName;
    this.categoryService.addCategory(this.category)
    .subscribe((resp)=>console.log(resp)
    ,(err)=>console.log(err));
  }

}
