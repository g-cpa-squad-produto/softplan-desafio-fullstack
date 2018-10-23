import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavComponent } from './nav.component';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [NavComponent],
  exports: [NavComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]

})
export class NavModule { }
