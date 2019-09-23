import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

import {Observable, throwError} from 'rxjs';
import {environment} from '../../../environments/environment';
import {Usuario} from '../models/pessoa.model';
import {catchError} from 'rxjs/operators';

@Injectable()
export class UsuarioService {

  constructor(private http: HttpClient) {
  }

  private handleError(error: any): Observable<any> {
    console.log('ERRO NA REQUISIÇÃO => ', error);
    return throwError(error);
  }

  buscarUsuarios(): Observable<any> {
    console.log('Buscando usuários');
    return this.http.get<any>(`${environment.app_admin}/usuarios`);
  }

  cadastrar(usuario: Usuario): Observable<any> {
    console.log('Inserindo Usuários');
    console.log(usuario);
    return this.http.post<any>(`${environment.app_admin}/usuarios`, usuario).pipe(catchError(this.handleError));
  }

  editar(usuario: Usuario): Observable<any> {
    console.log('Editando Usuários');
    console.log(usuario);
    return this.http.put<any>(`${environment.app_admin}/usuarios`, usuario).pipe(catchError(this.handleError));
  }
}
