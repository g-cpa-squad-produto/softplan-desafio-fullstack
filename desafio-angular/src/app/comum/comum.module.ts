import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MaterializeModule } from 'angular2-materialize';

import { ModalAlertaComponent } from './modal-alerta/modal-alerta.component';

@NgModule({
  imports: [
    CommonModule,
    MaterializeModule
  ],
  declarations: [
    ModalAlertaComponent
  ],
  exports:[
    ModalAlertaComponent
  ]
})
export class ComumModule { }
