import { AdmComponent } from './../../../adm/adm.component';
import { EnumPermissaoUsuario } from './../../../entidades/enumPermissaoUsuario';
import { Usuario } from './../../../entidades/usuario';
import { UsuarioService } from './../../../servicos/usuario.service';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';


@Component({
  selector: 'app-list-usu',
  templateUrl: './list-usu.component.html',
  styleUrls: ['./list-usu.component.css']
})
export class ListUsuComponent implements OnInit {

  lstUsuario = Array<Usuario>();

  constructor(
    private _location: Location,
    private _router: Router,
    private _usuarioService: UsuarioService,
    private _admComponent: AdmComponent
  ) { }

  ngOnInit() {
    this._usuarioService.recuperaTodosUsuarios().subscribe(data => {
      this.lstUsuario = (data);
    });
  }

  redirecionaPagina(urlPage: string) {
    this._router.navigate([urlPage]);
  }

  excluir(idUsu) {
    this._usuarioService.excluirUsuarioPorId(idUsu).subscribe(data => {
      if (data) {
        this.lstUsuario = this.lstUsuario.filter(usu => usu.id !== idUsu);
        const textMsg = 'Usuário excluido com sucesso!';
        this._admComponent.msgToast(textMsg);
      }
    });
  }

  getDescPermissao(lstP: Array<EnumPermissaoUsuario>) {
    let desc = '';
    for (let index = 0; index < lstP.length; index++) {
      if (index !== 0) {
        desc += ', ';
      }
      desc += lstP[index].toString().charAt(0).toUpperCase() + lstP[index].toString().toLowerCase().slice(1);
    }
    return desc;
  }

  voltarPagina() {
    this._location.back();
  }
}
