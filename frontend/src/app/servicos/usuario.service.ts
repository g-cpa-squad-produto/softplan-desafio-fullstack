import { EnumPermissaoUsuario } from './../entidades/enumPermissaoUsuario';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Http, RequestOptions } from '@angular/http';
import { map, catchError } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';
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

  GetHttpHeaders(): HttpHeaders {
    const headers = new HttpHeaders()
      .set('Authorization', localStorage.getItem('token'));

    return headers;
  }

  constructor(
    private http: HttpClient
  ) { }

  recuperaTodosUsuarios()  {
    // tslint:disable-next-line:max-line-length
    return this.http.get<Usuario[]>(this.urlListarTodos, { headers: this.GetHttpHeaders() } ).pipe(catchError(this.handleError));
  }

  recuperaTodosUsuariosPermissao(permissao) {
    // tslint:disable-next-line:max-line-length
    return this.http.get<Usuario[]>(this.urlListarTodosPorPermissao.replace(':PERM', permissao.toString()), { headers: this.GetHttpHeaders() }).pipe(catchError(this.handleError));
  }

  recuperarUsuarioPorId(idUsu) {
    // tslint:disable-next-line:max-line-length
    return this.http.get<Usuario>(this.urlListarPorId.replace(':ID', idUsu.toString()), { headers: this.GetHttpHeaders() }).pipe(catchError(this.handleError));
  }

  excluirUsuarioPorId(idUsu) {
    // tslint:disable-next-line:max-line-length
    return this.http.delete<boolean>(this.urlExcluirPorId.replace(':ID', idUsu.toString()), { headers: this.GetHttpHeaders() }).pipe(catchError(this.handleError));
  }

  recuperaTodasPermissoes() {
    // tslint:disable-next-line:max-line-length
    return this.http.get<EnumPermissaoUsuario[]>(this.urlListarEnumPermissoes, { headers: this.GetHttpHeaders() }).pipe(catchError(this.handleError));
  }

  salvar(usuario: Usuario) {
    // tslint:disable-next-line:max-line-length
    return this.http.post<Usuario>(this.urlSalvar, usuario, { headers: this.GetHttpHeaders() }).pipe(catchError(this.handleError));
  }

  login(usuario: Usuario) {
    return this.http.post(this.urlLogin, usuario, {responseType: 'text'}).pipe(map(r => r.toString()));
  }
  private handleError(err: HttpErrorResponse) {
      console.log(err.message);
      return Observable.throw(err.message);
  }
}
