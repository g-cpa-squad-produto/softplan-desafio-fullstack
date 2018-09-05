import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { UsuarioService } from '../usuario.service';
import { Usuario } from '../usuario.model';

@Component({
  selector: 'app-form-usuario',
  templateUrl: './form-usuario.component.html',
  styleUrls: ['./form-usuario.component.css']
})
export class FormUsuarioComponent implements OnInit {

  inclusao: boolean;
  usuario: Usuario = new Usuario();

  constructor(
    private srv: UsuarioService, 
    private router: Router, 
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      const idUsuario = params["idUsuario"];
      console.log(idUsuario);
      if (idUsuario) {
        this.srv.consultar(idUsuario).subscribe(
          (retorno: Usuario) => this.usuario = retorno,
          (error) => console.log(error)
        );
        this.inclusao = false;
      } else {
        // this.usuario = new Usuario();
        this.inclusao = true;
      }
    });

  }

  salvar() {
    if (this.inclusao) {
      this.srv.incluir(this.usuario).subscribe(
        (retorno: Usuario) => { 
          console.log(retorno);
          this.retornarConsulta(); 
        },
        (error) => console.log(error)
      );
    } else {
      this.srv.alterar(this.usuario).subscribe(
        (retorno: Usuario) => {
          console.log(retorno);
          this.retornarConsulta();
        },
        (error) => console.log(error)
      );
    }
  }

  cancelar() {
    this.retornarConsulta();
  }
  
  retornarConsulta() {
    this.router.navigate(['/usuario/consulta']);
  }
}
