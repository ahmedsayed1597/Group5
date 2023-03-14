import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PathLoginGuard } from './guards/path-login.guard';
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
  {path:'admin/product' , component:ProductComponent},
  {path:'admin/showProduct' , component:ShowProductComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
