import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './home.component';
import { ListTableModule } from 'src/app/components/list-table/list-table.module';
import { LoginModule } from '../login/login.module';
import { RouterModule } from '@angular/router';
import { HomeRoutingModule } from './home.routing.module';
import { UsersModule } from '../users/users.module';
import { NavModule } from 'src/app/components/nav/nav.module';
import { ContainerModule } from 'src/app/components/container/container.module';

@NgModule({
  imports: [
    CommonModule,
        NavModule,
        ListTableModule,
        LoginModule,
        UsersModule,
        ContainerModule,
        RouterModule,
        HomeRoutingModule
  ],
  declarations: [HomeComponent],
  exports: [HomeComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HomeModule { }
