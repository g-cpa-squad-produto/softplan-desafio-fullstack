import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListFinalizeComponent } from './list-finalize/list-finalize.component';
import { ShowFinalizeComponent } from './show-finalize/show-finalize.component';
import { FormFinalizeComponent } from './form-finalize/form-finalize.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  imports: [
    CommonModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
  ],
  declarations: [ ListFinalizeComponent, ShowFinalizeComponent, FormFinalizeComponent]
})
export class FinalizeModule { }
