import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { GenericServerService } from '../generic-server.service';
import { map } from 'rxjs/operators';
import { Usuario } from './usuario.entity';

@Injectable({
  providedIn: 'root'
})

export class UsuarioService extends GenericServerService {
  private api: string;

  constructor(private http: HttpClient) {
    super();
    this.api = this.getUrl() + '/usuarios/';
  }

  listarTodos(): Observable<any> {
    return this.http.get<any>(`${this.api}`).pipe(map(res => res));
  }

  inserir(usuario: Usuario) {
    return this.http.post(`${this.api}`, JSON.stringify(usuario), this.httpOptions)
    .pipe(map(res => res));
  }

  salvar(usuario: Usuario) {
    return this.http.put(`${this.api}`, JSON.stringify(usuario), this.httpOptions)
    .pipe(map(res => res));
  }

  apagar(id: string) {
    return this.http.delete(`${this.api}excluir/${id}`, this.httpOptions)
    .pipe(map(res => res));
  }

  getUsuarioById(id: string): Observable<Usuario> {
    return this.http.get<Usuario>(`${this.api}editar/${id}`).pipe(map(res => res));
  }
}
