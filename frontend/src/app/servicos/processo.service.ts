import { Parecer } from './../entidades/parecer';
import { Processo } from './../entidades/processo';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { map } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProcessoService {

  private urlListarTodos = 'http://localhost:8080/processo/todos-processos';
  private urlListarPorId = 'http://localhost:8080/processo/processo-por-id/:ID';
  private urlSalvar = 'http://localhost:8080/processo/processo-salvar';
  private urlSalvarParecer = 'http://localhost:8080/processo/processo-salvar-parecer';
  private urlListarTodosByUsu = 'http://localhost:8080/processo/todos-processos-id-usu/:ID';

  GetHttpHeaders(): HttpHeaders {
    const headers = new HttpHeaders()
      .set('content-type', 'application/json')
      .set('Authorization', localStorage.getItem('token'));

    return headers;
  }

  constructor(private http: HttpClient) { }

  recuperaTodosProcessos() {
    return this.http.get<Processo[]>(this.urlListarTodos, { headers: this.GetHttpHeaders() }).pipe();
  }

  recuperarProcessoPorId(idProcesso) {
    // tslint:disable-next-line:max-line-length
    return this.http.get<Processo>(this.urlListarPorId.replace(':ID', idProcesso.toString()), { headers: this.GetHttpHeaders() }).pipe();
  }

  recuperaTodosProcessosPendentesPorUsuario(idUsu) {
    // tslint:disable-next-line:max-line-length
    return this.http.get<Processo[]>(this.urlListarTodosByUsu.replace(':ID', idUsu.toString()), { headers: this.GetHttpHeaders() }).pipe();
  }

  salvar(processo: Processo) {
    return this.http.post<Processo>(this.urlSalvar, processo, { headers: this.GetHttpHeaders() }).pipe();
  }

  salvarParecer(parecer: Parecer) {
    return this.http.post<Parecer>(this.urlSalvarParecer, parecer, { headers: this.GetHttpHeaders() }).pipe();
  }

  private handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      console.error('Um erroocorreu:', error.error.message);
    } else {
      console.error(
        'Backend returned code ${error.status}, ' +
        'body was: ${error.error}');
    }
    return throwError(
      'Erro, tente de novo.');
  }
}
