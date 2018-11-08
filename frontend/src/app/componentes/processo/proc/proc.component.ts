import { Usuario } from './../../../entidades/usuario';
import { UsuarioService } from './../../../servicos/usuario.service';
import { AdmComponent } from './../../../adm/adm.component';
import { ActivatedRoute } from '@angular/router';
import { ProcessoService } from './../../../servicos/processo.service';
import { Processo } from './../../../entidades/processo';
import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-proc',
  templateUrl: './proc.component.html',
  styleUrls: ['./proc.component.css']
})
export class ProcComponent implements OnInit {

  processo: Processo;
  lstTodosUsuTipoFinalizador = Array<Usuario>();

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
    let idParam;
    this._usuarioService.recuperaTodosUsuariosPermissao('FINALIZADOR').subscribe(
      res => {
        this.lstTodosUsuTipoFinalizador = (res);
        //
        this._route.params.subscribe(
          params => {
            idParam = params['id'];
            if (idParam != null) {
              this._processoService.recuperarProcessoPorId(idParam).subscribe(
                ret => {
                  this.processo = (ret);
                  for (let index = 0; index < this.processo.lstUsuParecer.length; index++) {
                    document.getElementById(this.processo.lstUsuParecer[index].id.toString()).setAttribute('checked', 'true');
                  }
                }
              );
            }
          }
        );
      }
    );
  }

  validaCadastro(): boolean {
    if (this.processo.descricao !== undefined &&
      this.processo.lstUsuParecer !== undefined &&
      this.processo.descricao !== '' &&
      this.processo.lstUsuParecer.length > 0
    ) {
      return true;
    } else {
      return false;
    }
  }

  salvar() {
    let textMsg = '';
    if (this.validaCadastro()) {
      this._processoService.salvar(this.processo).subscribe(
        data => {
          if (data.id !== undefined && data.id !== null ) {
            textMsg = 'Processo cadastrado com sucesso!';
          } else {
            textMsg = 'Erro ao cadastrar processo.';
          }
          this._admComponent.msgToast(textMsg);
        });
    } else {
      textMsg = 'Erro ao cadastrar processo. Preencha todos os campos';
      this._admComponent.msgToast(textMsg);
    }
  }

  redirecionaPagina(urlPage: string) {
    this._router.navigate([urlPage]);
  }

  addProcesso(usuario: Usuario) {
    if (this.processo.lstUsuParecer === undefined) {
      this.processo.lstUsuParecer = Array<Usuario>();
    }
    if (this.processo.lstUsuParecer.indexOf(usuario) === -1) {
      this.processo.lstUsuParecer.push(usuario);
    } else {
      this.processo.lstUsuParecer = this.processo.lstUsuParecer.filter(usuP => usuP.id !== usuario.id);
    }
  }

  voltarPagina() {
    this._location.back();
  }

}
