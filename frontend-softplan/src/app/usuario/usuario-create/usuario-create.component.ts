import { Component, OnInit } from '@angular/core';
import { Usuario } from '../usuario.entity';
import { UsuarioService } from '../usuario.service';
import { Router, ActivatedRoute } from '@angular/router';
import { ResponseModel } from 'src/app/response/response.entity';

@Component({
  selector: 'app-usuario-create',
  templateUrl: './usuario-create.component.html',
  styleUrls: ['./usuario-create.component.css']
})
export class UsuarioCreateComponent implements OnInit {

  usuario: Usuario = new Usuario();
  titulo: String;
  private response: ResponseModel = new ResponseModel();
  constructor(private usuarioService: UsuarioService,
              private router: Router,
              private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.activatedRoute.params.subscribe(parameters => {
      if (parameters['id'] !== undefined) {
        this.titulo = 'Editar Usuário';
        this.usuarioService.getUsuarioById(parameters['id']).subscribe(res => this.usuario = res);
      } else {
        this.titulo = 'Criar Usuário';
      }
    });
  }

  voltar() {
    this.router.navigate(['./usuario']);
  }

  salvar() {
    if (this.usuario.id === undefined) {
      this.usuarioService.inserir(this.usuario).subscribe(res => {
        this.response = <ResponseModel>res;
        if (this.response.id === 1) {
          alert(this.response.mensagem);
          this.voltar();
        } else {
          alert(this.response.mensagem);
        }
      }
      );
    } else {
      this.usuarioService.salvar(this.usuario).subscribe(res => {
        this.response = <ResponseModel>res;
        if (this.response.id === 1) {
          alert(this.response.mensagem);
          this.voltar();
        } else {
          alert(this.response.mensagem);
        }
      });
    }
  }

}
