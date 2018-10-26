import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './home.component';
import { LoginModule } from '../login/login.module';
import { RouterModule } from '@angular/router';
import { HomeRoutingModule } from './home.routing.module';
import { UsersModule } from '../users/users.module';
import { NavModule } from 'src/app/components/nav/nav.module';
import { ScreeningModule } from '../screening/screening.module';
import { FinalizeModule } from '../finalize/finalize.module';

@NgModule({
  imports: [
    CommonModule,
        NavModule,
        LoginModule,
        UsersModule,
        ScreeningModule,
        FinalizeModule,
        RouterModule,
        HomeRoutingModule
  ],
  declarations: [HomeComponent],
  exports: [HomeComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HomeModule { }
