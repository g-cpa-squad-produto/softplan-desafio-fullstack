import { Usuario } from './../../../../entidades/usuario';
import { Location } from '@angular/common';
import { Parecer } from './../../../../entidades/parecer';
import { AdmComponent } from './../../../../adm/adm.component';
import { UsuarioService } from './../../../../servicos/usuario.service';
import { ProcessoService } from './../../../../servicos/processo.service';
import { Processo } from './../../../../entidades/processo';
import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-pendente',
  templateUrl: './pendente.component.html',
  styleUrls: ['./pendente.component.css']
})
export class PendenteComponent implements OnInit {

  processo: Processo;
  parecer: Parecer;
  usuarioLogado: Usuario;

  constructor(
    private _location: Location,
    private _processoService: ProcessoService,
    private _usuarioService: UsuarioService,
    private _admComponent: AdmComponent,
    private _route: ActivatedRoute,
    private _router: Router

  ) { }

  ngOnInit() {
    this.processo = new Processo();
    this.usuarioLogado = new Usuario();
    let idParam;
    //ID PROCESSO
    this._route.params.subscribe(
      params => {
        idParam = params['id'];
        if (idParam != null) {
          this._processoService.recuperarProcessoPorId(idParam).subscribe(
            ret => {
              this.processo = (ret);
            }
          );
        }
      }
    );
  }

  validaCadastro(): boolean {
    if (this.parecer.parecer !== undefined &&
      this.parecer.parecer !== null &&
      this.parecer.parecer !== '') {
      return true;
    } else {
      return false;
    }
  }

  salvar() {
    let textMsg = '';
    //VALIDA SE CAMPOS ESTAO PREENCHIDOS
    if (this.validaCadastro()) {
      //SETA PROVESSO
      this.parecer.processo = this.processo;
      //SETA USUARIO
      this.parecer.usuarioCriacao = this.usuarioLogado;
      //CHAMA REST PARA ATUALIZAR
      this._processoService.salvarParecer(this.parecer).subscribe(
        data => {
          if (data.id !== undefined && data.id !== null ) {
            textMsg = 'Parecer cadastrado com sucesso!';
          } else {
            textMsg = 'Erro ao cadastrar parecer.';
          }
          this._admComponent.msgToast(textMsg);
        });
    } else {
      textMsg = 'Erro ao cadastrar parecer. Preencha todos os campos';
      this._admComponent.msgToast(textMsg);
    }
  }

  //ROTEIA PARA A URLSUGERIDA
  redirecionaPagina(urlPage: string) {
    this._router.navigate([urlPage]);
  }

  //RETORNA PARA ULTIMA URL
  voltarPagina() {
    this._location.back();
  }

  changeTextArea (ev) {
      this.parecer.parecer = ev.target.value;
  }

}
