import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddressComponent } from './address/address.component';
import { AdminComponent } from './adminPages/admin/admin.component';
import { AddCategoryComponent } from './categories/add-category/add-category.component';
import { EditCategoryComponent } from './categories/edit-category/edit-category.component';
import { ShowCategoryComponent } from './categories/show-category/show-category.component';
import { EditProductComponent } from './edit-product/edit-product.component';
import { PathLoginGuard } from './guards/path-login.guard';
import { PathRoleGuard } from './guards/path-role.guard';
import { LoginComponent } from './login/login.component';
import { CartComponent } from './pages/cart/cart.component';
import { HomeComponent } from './pages/home/home.component';
import { ProductComponent } from './product/product.component';
import { RegisterComponent } from './register/register.component';
import { ShowProductComponent } from './show-product/show-product.component';

const routes: Routes = [
  {path: '', redirectTo:'home', pathMatch:'full'},
  {path: 'home',component: HomeComponent},
  {path: 'cart',component: CartComponent,},
  {path: 'register', component:RegisterComponent, canActivate:[PathLoginGuard]},
  {path:'login' , component:LoginComponent, canActivate:[PathLoginGuard]},
  {path:'admin/showProduct' , component:ShowProductComponent, canActivate:[PathRoleGuard]},
  {path: 'admin/add' , component:AddCategoryComponent, canActivate:[PathRoleGuard]},
  {path: 'admin/showCategory' , component:ShowCategoryComponent, canActivate:[PathRoleGuard]},
  {path: 'admin/editCategory/:categoryId' , component:EditCategoryComponent, canActivate:[PathRoleGuard]},
  {path: 'admin/dashboard' , component:AdminComponent, canActivate:[PathRoleGuard]},
  {path: 'admin/editProduct/:productId' , component:EditProductComponent,canActivate:[PathRoleGuard]},
  {path: 'address' , component:AddressComponent}
  // {path: 'admin/editCategory/:categoryId' , component:EditCategoryComponent},
  // {path: 'admin/editCategory/:categoryId' , component:EditCategoryComponent},



];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
