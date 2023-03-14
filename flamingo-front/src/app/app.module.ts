import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HomeComponent } from './pages/home/home.component';

import { MatSidenavModule } from '@angular/material/sidenav';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatMenuModule } from '@angular/material/menu';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatTreeModule } from '@angular/material/tree';
import { MatListModule } from '@angular/material/list';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatTableModule } from '@angular/material/table';
import { MatBadgeModule } from '@angular/material/badge';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import {MatFormFieldModule} from '@angular/material/form-field';

import { ProductsHeaderComponent } from './pages/home/components/products-header/products-header.component';
import { ProductBoxComponent } from './pages/home/components/product-box/product-box.component';
import { FiltersComponent } from './pages/home/components/filters/filters.component';
import { HeaderComponent } from './components/header/header.component';
import { CartComponent } from './pages/cart/cart.component';
import { CartService } from './services/cart.service';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { StoreService } from './services/store.service';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ReactiveFormsModule } from '@angular/forms';
import { ProductComponent } from './product/product.component';
import { MatInputModule } from '@angular/material/input';
import { ShowProductComponent } from './show-product/show-product.component';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSelectModule } from '@angular/material/select';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner'; 
import { InterceptorService } from './services/interceptor.service';
import { AddCategoryComponent } from './add-category/add-category.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ProductsHeaderComponent,
    ProductBoxComponent,
    FiltersComponent,
    HeaderComponent,
    CartComponent,
    LoginComponent,
    RegisterComponent,
    ProductComponent,
    ShowProductComponent,
    AddCategoryComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatSidenavModule,
    MatGridListModule,
    MatMenuModule,
    MatButtonModule,
    MatCardModule,
    MatIconModule,
    MatExpansionModule,
    MatTreeModule,
    MatListModule,
    MatToolbarModule,
    MatTableModule,
    MatBadgeModule,
    MatSnackBarModule,
    BrowserAnimationsModule,
    HttpClientModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    MatPaginatorModule,
    MatSelectModule,
    MatProgressSpinnerModule
  ],
  providers: [CartService, StoreService, {provide: HTTP_INTERCEPTORS, useClass:InterceptorService, multi:true}],
  bootstrap: [AppComponent],
})
export class AppModule {}
