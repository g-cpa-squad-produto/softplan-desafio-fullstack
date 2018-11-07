import { AdmComponent } from './../../../adm/adm.component';
import { UsuarioService } from './../../../servicos/usuario.service';
import { EnumPermissaoUsuario } from './../../../entidades/enumPermissaoUsuario';
import { Usuario } from './../../../entidades/usuario';
import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-usu',
  templateUrl: './usu.component.html',
  styleUrls: ['./usu.component.css']
})
export class UsuComponent implements OnInit {
  usuario: Usuario;
  lstEnumTipoPerm = Array<EnumPermissaoUsuario>();

  constructor(
    private _location: Location,
    private _usuarioService: UsuarioService,
    private _admComponent: AdmComponent,
    private _route: ActivatedRoute
  ) { }

  ngOnInit() {
    let idParam: string;
    this.usuario = new Usuario();
    this._usuarioService.recuperaTodasPermissoes().subscribe(
      res => {
        this.lstEnumTipoPerm = (res);
        this._route.params.subscribe(params => {
          idParam = params['id'];
        });
        if (idParam != null) {
          this._usuarioService.recuperarUsuarioPorId(idParam).subscribe(
            ret => {
              this.usuario = (ret);
              for (let index = 0; index < this.usuario.lstPermissao.length; index++) {
                document.getElementById(this.usuario.lstPermissao[index].toString()).setAttribute('checked', 'true');
              }
            }
          );
        }
      }
    );
  }

  validaChecked(): boolean {
    return true;
  }

  validaCadastro(): boolean {
    if (this.usuario.login !== undefined &&
      this.usuario.senha !== undefined &&
      this.usuario.nome !== undefined &&
      this.usuario.login !== '' &&
      this.usuario.senha !== '' &&
      this.usuario.nome !== ''
    ) {
      return true;
    } else {
      return false;
    }
  }

  salvar() {
    let textMsg = '';
    if (this.validaCadastro()) {
      this._usuarioService.salvar(this.usuario).subscribe(
        data => {
          if (data.id !== undefined && data.id !== null ) {
            textMsg = 'Usuário cadastrado com sucesso!';
            this.usuario = new Usuario();
          } else {
            textMsg = 'Erro ao cadastrar usuário.';
          }
          this._admComponent.msgToast(textMsg);
        });
    } else {
      textMsg = 'Erro ao cadastrar usuário. Preencha todos os campos';
      this._admComponent.msgToast(textMsg);
    }
  }

  addPermissao(enumPerm: EnumPermissaoUsuario) {
    if (this.usuario.lstPermissao === undefined) {
      this.usuario.lstPermissao = Array<EnumPermissaoUsuario>();
    }
    if (this.usuario.lstPermissao.indexOf(enumPerm) === -1) {
      this.usuario.lstPermissao.push(enumPerm);
    } else {
      this.usuario.lstPermissao = this.usuario.lstPermissao.filter(enumP => enumP !== enumPerm);
    }
  }

  voltarPagina() {
    this._location.back();
  }

}
