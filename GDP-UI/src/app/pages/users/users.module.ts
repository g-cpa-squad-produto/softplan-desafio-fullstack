import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserListComponent } from './user-list/user-list.component';
import { ShowUserComponent } from './show-user/show-user.component';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormUserComponent } from './form-user/form-user.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';

@NgModule({
  imports: [
    CommonModule,
    BrowserModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    FormsModule,
  ],
  exports: [CommonModule],
  declarations: [
    UserListComponent,
    ShowUserComponent,
    FormUserComponent
  ]
})
export class UsersModule { }
