import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MaterializeModule } from 'angular2-materialize';

import { GridUsuarioComponent } from './grid-usuario/grid-usuario.component';
import { UsuarioService } from './usuario.service';
import { FormUsuarioComponent } from './form-usuario/form-usuario.component';
import { ConsultaUsuarioComponent } from './consulta-usuario/consulta-usuario.component';
import { ComumModule } from '../comum/comum.module';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    MaterializeModule,
    ComumModule
  ],
  declarations: [
    GridUsuarioComponent,
    FormUsuarioComponent,
    ConsultaUsuarioComponent,
  ], 
  providers: [
    UsuarioService
  ]
})
export class UsuarioModule { }
