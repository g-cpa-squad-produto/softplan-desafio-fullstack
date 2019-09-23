import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

import {Observable, throwError} from 'rxjs';
import {environment} from '../../../environments/environment';
import {catchError} from 'rxjs/operators';
import {Processo} from '../models/processo.model';

@Injectable()
export class ProcessoService {

  constructor(private http: HttpClient) {
  }

  private handleError(error: any): Observable<any> {
    console.log('ERRO NA REQUISIÇÃO => ', error);
    return throwError(error);
  }

  cadastrar(processo: Processo): Observable<any> {
    console.log('Inserindo processo');
    console.log(processo);
    return this.http.post<any>(`${environment.app_admin}/processos`, processo).pipe(catchError(this.handleError));
  }

  atualizar(processo: Processo): Observable<any> {
    console.log('Atualizando processo');
    console.log(processo);
    return this.http.put<any>(`${environment.app_admin}/processos`, processo).pipe(catchError(this.handleError));
  }

  buscarTodosTriador(id: number): Observable<any> {
    console.log('buscando processos por triador');
    return this.http.get<any>(`${environment.app_admin}/processos/triador/${id}`).pipe(catchError(this.handleError));
  }

  buscarTodosFinalizador(id: number): Observable<any> {
    console.log('buscando processos por finalizador');
    return this.http.get<any>(`${environment.app_admin}/processos/finalizador/${id}`).pipe(catchError(this.handleError));
  }

  buscarTodosPendentes(): Observable<any> {
    console.log('buscando processos pendentes');
    return this.http.get<any>(`${environment.app_admin}/processos/pendentes/`).pipe(catchError(this.handleError));
  }
}
