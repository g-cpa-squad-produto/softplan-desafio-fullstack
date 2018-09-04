import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UsuarioComponent } from './usuario.component';
import { GridUsuarioComponent } from './grid-usuario/grid-usuario.component';
import { UsuarioService } from './usuario.service';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [
    UsuarioComponent,
    GridUsuarioComponent
  ], 
  providers: [
    UsuarioService
  ]
})
export class UsuarioModule { }
