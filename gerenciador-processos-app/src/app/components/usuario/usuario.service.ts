import { Injectable } from '@angular/core';
import {Observable, of} from 'rxjs';
import {Processo} from '../../model/processo.model';
import {HttpClient} from '@angular/common/http';
import {MessageService} from '../../message.service';
import {API} from '../../mock-api';
import {catchError, tap} from 'rxjs/operators';
import {Usuario} from '../../model/usuario.model';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  usuario: Observable<Usuario>;

  constructor(private http: HttpClient, private messageService: MessageService) { }

  getUsuarios(): Observable<Usuario[]> {
    return this.http.get<Usuario[]>( `${API}/usuario`)
      .pipe(
        tap(_ => this.log('usuários encontrados')),
        catchError(this.handleError<Usuario[]>('getUsuarios', []))
      );
  }

  addUsuario(usuario: Usuario): Observable<Usuario> {
    return this.http.post<Usuario>(`${API}/usuario/`, usuario).pipe(
      tap((newUsuario: Usuario) => this.log(`Usuario adicionado com id=${newUsuario.id}`)),
      catchError(this.handleError<Usuario>('addHero'))
    );
  }

  getUsuario(id: number): Observable<Usuario> {
    const url = `${API}/usuario/${id}`;
    return this.http.get<Usuario>(url).pipe(
      tap(_ => this.log(`usuario encontrado id=${id}`)),
      catchError(this.handleError<Usuario>(`getUsuario id=${id}`))
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
    this.messageService.add(`UsuarioService: ${message}`);
  }
}
