import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

import { Usuario } from '../usuario.model';

@Component({
  selector: 'app-grid-usuario',
  templateUrl: './grid-usuario.component.html',
  styleUrls: ['./grid-usuario.component.css']
})
export class GridUsuarioComponent implements OnInit {

  @Input()
  lista: Usuario[] = [];

  @Output()
  alterarUsuario = new EventEmitter();
  @Output()
  excluirUsuario = new EventEmitter();

  constructor() { }

  ngOnInit() {
  }

}
