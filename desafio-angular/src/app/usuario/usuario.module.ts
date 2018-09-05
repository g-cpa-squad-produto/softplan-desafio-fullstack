import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';

import { GridUsuarioComponent } from './grid-usuario/grid-usuario.component';
import { UsuarioService } from './usuario.service';
import { FormUsuarioComponent } from './form-usuario/form-usuario.component';
import { ConsultaUsuarioComponent } from './consulta-usuario/consulta-usuario.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule
  ],
  declarations: [
    GridUsuarioComponent,
    FormUsuarioComponent,
    ConsultaUsuarioComponent
  ], 
  providers: [
    UsuarioService
  ]
})
export class UsuarioModule { }
