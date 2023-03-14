import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CategoryToAdd } from 'src/app/models/CategoryToAdd.model';
import { CategoryService } from 'src/app/services/category.service';

@Component({
  selector: 'app-edit-category',
  templateUrl: './edit-category.component.html',
  styleUrls: ['./edit-category.component.scss']
})
export class EditCategoryComponent implements OnInit {
  id:string;

  newCategory=new CategoryToAdd();
  constructor(private activateRoute:ActivatedRoute,
              private _service:CategoryService) { }

  ngOnInit(): void {
    this.activateRoute.paramMap.subscribe(
      param=>{this.id =param.get('categoryId')||'';}
    );
    this.getById(+this.id);
  }

  public edit(){
    console.log(this.newCategory.categoryName);
    return this._service.updateCategory(this.newCategory,+this.id).
    subscribe((response)=>console.log(response))
  }

  public getById(id:number){

    return this._service.getCategoryById(+this.id).subscribe(
      (resp)=>this.newCategory=resp
    )
  }

}
