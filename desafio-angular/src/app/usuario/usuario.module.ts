import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UsuarioComponent } from './usuario.component';
import { GridUsuarioComponent } from './grid-usuario/grid-usuario.component';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [
    UsuarioComponent,
    GridUsuarioComponent
  ]
})
export class UsuarioModule { }
