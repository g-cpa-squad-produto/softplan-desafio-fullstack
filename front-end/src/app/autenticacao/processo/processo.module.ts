import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CadastrarProcessoComponent, ProcessoComponent } from './components';
import {RouterModule} from '@angular/router';
import {FlexLayoutModule} from '@angular/flex-layout';



@NgModule({
  declarations: [CadastrarProcessoComponent, ProcessoComponent],
  imports: [
    CommonModule,
    RouterModule,
    FlexLayoutModule
  ]
})
export class ProcessoModule { }
