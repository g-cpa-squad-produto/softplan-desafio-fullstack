import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MatIconModule } from '@angular/material/icon';

import { PagesRoutingModule } from './pages-routing.module';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';

@NgModule({
    declarations: [
      PageNotFoundComponent
    ],
  imports: [
    CommonModule,
    MatIconModule,
    PagesRoutingModule
  ]
})
export class PagesModule { }
