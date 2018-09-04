import { UsuarioService } from './usuario.service';
import { Component, OnInit } from '@angular/core';

import { Usuario } from './usuario.model';

@Component({
  selector: 'app-usuario',
  templateUrl: './usuario.component.html',
  styleUrls: ['./usuario.component.css']
})
export class UsuarioComponent implements OnInit {

  listaUsuario: Usuario[];

  constructor(private srv: UsuarioService) { }

  ngOnInit() {
    this.srv.listar().subscribe((dados) => this.listaUsuario = dados);
  }

  alterar(id: Number) {
    console.log(id);
  }

  excluir(id: Number) {
    this.srv.excluir(id).subscribe(
      () => {},
      (error) => { console.log(error); }
    );
  }
}
