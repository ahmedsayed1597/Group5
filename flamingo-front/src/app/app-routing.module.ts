import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddCategoryComponent } from './add-category/add-category.component';
import { AdminComponent } from './adminPages/admin/admin.component';
import { EditCategoryComponent } from './categories/edit-category/edit-category.component';
import { ShowCategoryComponent } from './categories/show-category/show-category.component';
import { PathLoginGuard } from './guards/path-login.guard';
import { LoginComponent } from './login/login.component';
import { CartComponent } from './pages/cart/cart.component';
import { HomeComponent } from './pages/home/home.component';
import { ProductComponent } from './product/product.component';
import { RegisterComponent } from './register/register.component';
import { ShowProductComponent } from './show-product/show-product.component';

const routes: Routes = [
  {path: '', redirectTo:'home', pathMatch:'full'},
  {path: '*',component: HomeComponent},
  {path: 'home',component: HomeComponent},
  {path: 'cart',component: CartComponent,},
  {path: 'register', component:RegisterComponent, canActivate:[PathLoginGuard]},
  {path:'login' , component:LoginComponent, canActivate:[PathLoginGuard]},
  {path:'admin/product' , component:ProductComponent},
  {path:'admin/showProduct' , component:ShowProductComponent},
  {path: 'admin/category' , component:AddCategoryComponent},
  {path: 'admin/dashboard' , component:  AdminComponent},
  {path: 'admin/showCategory' , component:  ShowCategoryComponent},
  {path: 'admin/editCategory' , component:  EditCategoryComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
