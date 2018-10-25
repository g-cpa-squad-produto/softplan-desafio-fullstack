import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserListComponent } from './user-list/user-list.component';
import { NewUserComponent } from './new-user/new-user.component';
import { ShowUserComponent } from './show-user/show-user.component';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormUserComponent } from './form-user/form-user.component';

const components = [
    UserListComponent,
    NewUserComponent,
    ShowUserComponent,
    FormUserComponent
];

@NgModule({
  imports: [
    CommonModule,
    BrowserModule,
    BrowserAnimationsModule,
  ],
  exports: [CommonModule],
  declarations: [components]
})
export class UsersModule { }
