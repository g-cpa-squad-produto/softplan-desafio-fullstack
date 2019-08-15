import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CadastrarUsuarioComponent, UsuarioComponent } from './components';
import {RouterModule} from '@angular/router';
import {FlexLayoutModule} from '@angular/flex-layout';




@NgModule({
  declarations: [CadastrarUsuarioComponent, UsuarioComponent],
  imports: [
    CommonModule,
    RouterModule,
    FlexLayoutModule
  ]
})
export class UsuarioModule { }
