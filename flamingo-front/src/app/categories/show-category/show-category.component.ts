import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { CategoryView } from 'src/app/models/Category-view.model';
import { CategoryService } from 'src/app/services/category.service';

@Component({
  selector: 'app-show-category',
  templateUrl: './show-category.component.html',
  styleUrls: ['./show-category.component.scss']
})
export class ShowCategoryComponent implements OnInit {

  categories: CategoryView[]=[];


  constructor(private categoryService : CategoryService) { }

  ngOnInit(): void {
    this.getCategories();
  }

  public getCategories(){
    this.categoryService.getCategories()
   .subscribe((response)=>this.categories=response.data );
 }

 public deleteCategory(index:number){
  this.categoryService.deleteCategory(index).subscribe(
    (resp)=>console.log(resp)

  )
  this.getCategories();
 }
}
