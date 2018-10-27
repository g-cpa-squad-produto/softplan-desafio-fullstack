import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListScreeningComponent } from './list-screening/list-screening.component';
import { FormScreeningComponent } from './form-screening/form-screening.component';
import { ShowScreeningComponent } from './show-screening/show-screening.component';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ReactiveFormsModule } from '@angular/forms';
import { FormAssignComponent } from './form-assign/form-assign.component';

@NgModule({
  imports: [
    CommonModule,
    BrowserModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
  ],
  declarations: [ListScreeningComponent, FormScreeningComponent, ShowScreeningComponent, FormAssignComponent]
})
export class ScreeningModule { }
