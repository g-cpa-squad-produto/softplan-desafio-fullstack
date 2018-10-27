import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListFinalizeComponent } from './list-finalize/list-finalize.component';
import { ShowFinalizeComponent } from './show-finalize/show-finalize.component';
import { FormFinalizeComponent } from './form-finalize/form-finalize.component';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [ ListFinalizeComponent, ShowFinalizeComponent, FormFinalizeComponent]
})
export class FinalizeModule { }
