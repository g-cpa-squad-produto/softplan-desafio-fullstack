import { Processo } from './../entidades/processo';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProcessoService {

  private urlListarTodos = 'http://localhost:8080/processo/todos-processos';
  private urlListarPorId = 'http://localhost:8080/processo/processo-por-id/:ID';
  private urlSalvar = 'http://localhost:8080/processo/processo-salvar';

  constructor(private http: Http) { }

  recuperaTodosProcessos() {
    return this.http.get(this.urlListarTodos).pipe(map((response: any) => response.json()));
  }

  recuperarProcessoPorId(idProcesso) {
    return this.http.get(this.urlListarPorId.replace(':ID', idProcesso.toString())).pipe(map((response: any) => response.json()));
  }

  salvar(processo: Processo) {
    return this.http.post(this.urlSalvar, processo).pipe(map((response: any) => response.json()));
  }
}
