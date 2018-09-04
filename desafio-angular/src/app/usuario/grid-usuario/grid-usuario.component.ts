import { Component, OnInit } from '@angular/core';

import { Usuario } from '../usuario.model';

@Component({
  selector: 'app-grid-usuario',
  templateUrl: './grid-usuario.component.html',
  styleUrls: ['./grid-usuario.component.css']
})
export class GridUsuarioComponent implements OnInit {

  lista: Usuario[] = [
    new Usuario(1, "123", "João", "ADMINISTRADOR"),
    new Usuario(2, "456", "Bia", "TRIADOR"),
  ];

  constructor() { }

  ngOnInit() {
    console.log( this.lista );
  }

}
