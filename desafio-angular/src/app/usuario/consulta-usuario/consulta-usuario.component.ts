import { Component, OnInit } from '@angular/core';
import { Router, NavigationExtras } from '@angular/router';

import { Usuario } from './../usuario.model';
import { UsuarioService } from './../usuario.service';

@Component({
  selector: 'app-consulta-usuario',
  templateUrl: './consulta-usuario.component.html',
  styleUrls: ['./consulta-usuario.component.css']
})
export class ConsultaUsuarioComponent implements OnInit {
  
  listaUsuario: Usuario[];

  constructor(private srv: UsuarioService, private router: Router) { }

  ngOnInit() {
    this.srv.listar().subscribe(
      (dados) => this.listaUsuario = dados,
      (error) => console.log(error)
    );
  }

  alterar(id: Number) {
    const navigation: NavigationExtras = {
      queryParams: {
        "idUsuario": id
      }
    };
    this.router.navigate(['/usuario/form'], navigation);
  }

  excluir(id: Number) {
    this.srv.excluir(id).subscribe(
      () => {
        this.listaUsuario = [];
        this.srv.listar().subscribe((dados) => this.listaUsuario = dados); 
      },
      (error) => { console.log(error); }
    );
  }

  incluir() {
    this.router.navigate(['/usuario/form']);
  }
  
}
