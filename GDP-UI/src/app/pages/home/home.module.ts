import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './home.component';
import { SearchBarModule } from 'src/app/components/search-bar/search-bar.module';
import { LeftNavModule } from 'src/app/components/left-nav/left-nav.module';
import { ListTableModule } from 'src/app/components/list-table/list-table.module';
import { LoginModule } from '../login/login.module';
import { RouterModule } from '@angular/router';
import { HomeRoutingModule } from './home.routing.module';
import { UsersModule } from '../users/users.module';

@NgModule({
  imports: [
    CommonModule,
        SearchBarModule,
        LeftNavModule,
        ListTableModule,
        LoginModule,
        UsersModule,
        RouterModule,
        HomeRoutingModule
  ],
  declarations: [HomeComponent],
  exports: [HomeComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HomeModule { }
