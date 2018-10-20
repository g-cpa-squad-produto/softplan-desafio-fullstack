import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LeftNavComponent } from './left-nav.component';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [LeftNavComponent],
  exports: [LeftNavComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]

})
export class LeftNavModule { }
