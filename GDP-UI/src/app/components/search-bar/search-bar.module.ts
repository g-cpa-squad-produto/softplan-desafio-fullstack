import { NgModule, CUSTOM_ELEMENTS_SCHEMA  } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SearchBarComponent } from './search-bar.component';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [SearchBarComponent],
  exports: [SearchBarComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SearchBarModule { }
