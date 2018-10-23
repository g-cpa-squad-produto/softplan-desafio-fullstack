import { ContainerComponent } from './container.component';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';

@NgModule({
  imports: [CommonModule],
  declarations: [ContainerComponent],
  exports: [ContainerComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ContainerModule {}
