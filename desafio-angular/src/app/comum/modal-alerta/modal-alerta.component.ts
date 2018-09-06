import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { MaterializeAction } from 'angular2-materialize';

@Component({
  selector: 'app-modal-alerta',
  templateUrl: './modal-alerta.component.html',
  styleUrls: ['./modal-alerta.component.css']
})
export class ModalAlertaComponent implements OnInit {

  @Input()
  titulo: string;
  @Input()
  texto: string;

  @Input()
  modalActions; // = new EventEmitter<string|MaterializeAction>();

  constructor() { }

  ngOnInit() {
  }
  
  // openModal() {
  //   this.modalActions.emit({action:"modal",params:['open']});
  // }
  
  // closeModal() {
  //   this.modalActions.emit({action:"modal",params:['close']});
  // }

}
