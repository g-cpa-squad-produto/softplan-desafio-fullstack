import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {ActivatedRoute, Router} from '@angular/router';
import {Processo} from '../../model/processo';
import {ProcessosService} from '../../service/processos.service';
import {UsuariosService} from '../../../usuarios/service/usuarios.service';
import {UsuarioResumido} from '../../../usuarios/model/usuario-resumido';

@Component({
  selector: 'atribuir-usuarios-processos',
  templateUrl: './atribuir-usuarios-processos.component.html',
  styleUrls: ['./atribuir-usuarios-processos.component.css']
})
export class AtribuirUsuariosProcessosComponent implements OnInit {

  processo: Processo;
  usuarios$: Observable<Array<UsuarioResumido>>;

  constructor(private service: ProcessosService,
              private usuariosService: UsuariosService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit() {
    this.processo = this.route.snapshot.data.processo;
    this.usuarios$ = this.usuariosService.pesquisarUsuariosFinalizadores();

    if (!this.processo.usuariosPermissao) {
      this.processo.usuariosPermissao = [];
    }
  }

  salvar() {
    this.service.atribuirUsuariosAProcesso(this.processo).subscribe(
      () => this.router.navigate(['processos']),
      error => alert(error));
  }

  selecionarUsuario(usuario: UsuarioResumido) {
    const usuarioEncontrado = this.processo.usuariosPermissao.find((usuarioAdicionado) => {
      return usuario.id === usuarioAdicionado.id;
    });

    if (!usuarioEncontrado) {
      this.processo.usuariosPermissao.unshift(usuario);
    }
  }

  removerUsuario(usuario: UsuarioResumido) {
    this.processo.usuariosPermissao.splice(this.processo.usuariosPermissao.indexOf(usuario), 1);
  }

}
