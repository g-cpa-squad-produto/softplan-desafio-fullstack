import { Component, OnInit } from '@angular/core';
import { UsuarioService } from '../usuario.service';
import { Usuario } from '../usuario.entity';
import { ActivatedRoute, Router } from '@angular/router';
import { ResponseModel } from 'src/app/response/response.entity';

@Component({
  selector: 'app-usuario-visualizar',
  templateUrl: './usuario-visualizar.component.html',
  styleUrls: ['./usuario-visualizar.component.css']
})
export class UsuarioVisualizarComponent implements OnInit {

  usuario: Usuario = new Usuario();
  private index: number;
  private response: ResponseModel = new ResponseModel();
  constructor(private usuarioService: UsuarioService,
              private router: Router,
              private activatedRouter: ActivatedRoute) { }

  ngOnInit() {
    this.activatedRouter.params.subscribe(parameters => {
      this.usuarioService.getUsuarioById(parameters['id'])
          .subscribe(user => this.usuario = user);
    });
  }

  editar() {
    this.router.navigate(['./usuario/editar', this.usuario.id]);
  }

  voltar() {
    this.router.navigate(['./usuario']);
  }

  excluir() {
    if (confirm('Deseja realmente excluir esse registro?') === true) {
      this.usuarioService.apagar(this.usuario.id).subscribe(res => {
        this.response = <ResponseModel>res;
        if (this.response.id === 1) {
          alert(this.response.mensagem);
        } else {
          alert(this.response.mensagem);
        }
      });
      this.voltar();
    }
  }
}
