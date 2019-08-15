import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CadastrarParecerComponent, ParecerComponent } from './components';
import {RouterModule} from '@angular/router';
import {FlexLayoutModule} from '@angular/flex-layout';

import { from } from 'rxjs';



@NgModule({
  declarations: [CadastrarParecerComponent, ParecerComponent],
  imports: [
    CommonModule,
    RouterModule,
    FlexLayoutModule
  ]
})
export class ParecerModule { }
