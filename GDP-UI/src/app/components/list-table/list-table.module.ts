import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListTableComponent } from './list-table.component';

@NgModule({
  imports: [CommonModule],
  declarations: [ListTableComponent],
  exports: [ListTableComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ListTableModule {}
