import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminFrontComponent } from './admin-front/admin-front.component';
import { AdminFrontHeaderComponent } from '../admin-front-header/admin-front-header.component';
import { AdminFrontSidebarComponent } from '../admin-front-sidebar/admin-front-sidebar.component';

@NgModule({
  declarations: [
    AdminFrontComponent,
    AdminFrontHeaderComponent,
    AdminFrontSidebarComponent,
  ],
  imports: [
    CommonModule
  ]
})
export class AdminDAshModule { }
