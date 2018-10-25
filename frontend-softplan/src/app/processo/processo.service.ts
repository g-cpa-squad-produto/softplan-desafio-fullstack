import { Injectable } from '@angular/core';
import { GenericServerService } from '../generic-server.service';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Processo } from './processo.entity';

@Injectable({
  providedIn: 'root'
})
export class ProcessoService extends GenericServerService {

  private api: string;
  constructor(private http: HttpClient) {
    super();
    this.api = this.getUrl() + '/processos/';
   }

   listarTodos(): Observable<any> {
    return this.http.get<any>(`${this.api}`).pipe(map(res => res));
  }

  inserir(processo: Processo) {
    return this.http.post(`${this.api}`, JSON.stringify(processo), this.httpOptions)
    .pipe(map(res => res));
  }

  salvar(processo: Processo) {
    return this.http.put(`${this.api}`, JSON.stringify(processo), this.httpOptions)
    .pipe(map(res => res));
  }

  apagar(id: string) {
    return this.http.delete(`${this.api}excluir/${id}`, this.httpOptions)
    .pipe(map(res => res));
  }

  getProcessoById(id: string): Observable<Processo> {
    return this.http.get<Processo>(`${this.api}editar/${id}`).pipe(map(res => res));
  }

  getProcessoByNumero(numero: Number): Observable<Processo> {
    return this.http.get<Processo>(`${this.api}/${numero}`).pipe(map(res => res));
  }
}
