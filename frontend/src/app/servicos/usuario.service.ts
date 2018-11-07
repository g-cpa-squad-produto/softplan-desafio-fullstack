import { EnumPermissaoUsuario } from './../entidades/enumPermissaoUsuario';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { Usuario } from '../entidades/usuario';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  private urlListarTodos = 'http://localhost:8080/usuario/todos-usuario';
  private urlExcluirPorId = 'http://localhost:8080/usuario/usuario-remover/:ID';
  private urlListarPorId = 'http://localhost:8080/usuario/usuario-por-id/:ID';
  private urlListarEnumPermissoes = 'http://localhost:8080/usuario/todas-permissoes';
  private urlSalvar = 'http://localhost:8080/usuario/usuario-salvar';
  private urlLogin = 'http://localhost:8080/usuario/login-usuario';
  private urlListarTodosPorPermissao = 'http://localhost:8080/usuario/todos-usuarios-permissao/:PERM';

  constructor(private http: Http) { }

  recuperaTodosUsuarios() {
    return this.http.get(this.urlListarTodos).pipe(map((response: any) => response.json()));
  }

  recuperaTodosUsuariosPermissao(permissao) {
    // tslint:disable-next-line:max-line-length
    return this.http.get(this.urlListarTodosPorPermissao.replace(':PERM', permissao.toString())).pipe(map((response: any) => response.json()));
  }

  recuperarUsuarioPorId(idUsu) {
    return this.http.get(this.urlListarPorId.replace(':ID', idUsu.toString())).pipe(map((response: any) => response.json()));
  }

  excluirUsuarioPorId(idUsu) {
    return this.http.delete(this.urlExcluirPorId.replace(':ID', idUsu.toString())).pipe(map((response: any) => response.json()));
  }

  recuperaTodasPermissoes() {
    return this.http.get(this.urlListarEnumPermissoes).pipe(map((response: any) => response.json()));
  }

  salvar(usuario: Usuario) {
    return this.http.post(this.urlSalvar, usuario).pipe(map((response: any) => response.json()));
  }

  login(usuario: Usuario) {
    return this.http.post(this.urlLogin, usuario).pipe(map((response: any) => response.text()));
  }
}
