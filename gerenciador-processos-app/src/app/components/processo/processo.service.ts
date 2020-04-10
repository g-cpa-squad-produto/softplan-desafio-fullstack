import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Processo} from '../../model/processo.model';
import {Observable, of} from 'rxjs';
import {API} from '../../mock-api';
import {catchError, tap} from 'rxjs/operators';
import {MessageService} from '../../message.service';
import {Parecer} from '../../model/parecer.model';
import {NovoUsuarioProcesso} from '../../model/novo-usuario-processo.model';

@Injectable({
  providedIn: 'root'
})
export class ProcessoService {

  processo: Observable<Processo>;

  constructor(private http: HttpClient, private messageService: MessageService) { }

  getProcessos(): Observable<Processo[]> {
    return this.http.get<Processo[]>( `${API}/processo`)
      .pipe(
        tap(_ => this.log('processos encontrados')),
        catchError(this.handleError<Processo[]>('getProcessos', []))
      );
  }

  addProcesso(processo: Processo): Observable<Processo> {
    return this.http.post<Processo>(`${API}/processo/`, processo).pipe(
      tap((newProcesso: Processo) => this.log(`processo adicionado com id=${newProcesso.id}`)),
      catchError(this.handleError<Processo>('addProcesso'))
    );
  }

  addParecer(idProcesso: number, parecer: any): Observable<Parecer> {
    return this.http.put<Parecer>(`${API}/processo/parecer/${idProcesso}`, parecer).pipe(
      tap((newProcesso: Parecer) => this.log(`add parecer com id do processo=${idProcesso}`)),
      catchError(this.handleError<Parecer>('addParecer'))
    );
  }

  addUsuarios(idProcesso: number, usuarios: any): Observable<NovoUsuarioProcesso> {
    const novosUsuarios = new NovoUsuarioProcesso(idProcesso, usuarios);
    console.log(novosUsuarios);
    return this.http.put<NovoUsuarioProcesso>(`${API}/processo/usuario`, novosUsuarios).pipe(
      tap((newProcesso: NovoUsuarioProcesso) => this.log(`add usuarios com id do processo=${idProcesso}`)),
      catchError(this.handleError<NovoUsuarioProcesso>('addUsuarios'))
    );
  }

  getProcesso(id: number): Observable<Processo> {
    const url = `${API}/processo/${id}`;
    return this.http.get<Processo>(url).pipe(
      tap(_ => this.log(`processos encontrado id=${id}`)),
      catchError(this.handleError<Processo>(`getProcesso id=${id}`))
    );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error); // log to console instead
      this.log(`${operation} failed: ${error.message}`);
      return of(result as T);
    };
  }

  private log(message: string) {
    this.messageService.add(`ProcessoService: ${message}`);
  }

}
