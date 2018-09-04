import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Usuario } from './usuario.model';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {
  
  private API_USUARIO: string = 'http://localhost:8080/usuario';

  constructor(private httpClient: HttpClient) { }

  listar() : Observable<Usuario[]> {
    return this.httpClient.get<Usuario[]>(this.API_USUARIO);
  }

  consultar(id: Number) : Observable<Usuario> {
    return this.httpClient.get<Usuario>(this.API_USUARIO + "/" + id);
  }

  incluir(usuario: Usuario) : Observable<Usuario> {
    return this.httpClient.post<Usuario>(this.API_USUARIO, usuario);
  }

  alterar(usuario: Usuario) {
    return this.httpClient.put<Usuario>(this.API_USUARIO, usuario);
  }

  excluir(id: Number) {
    return this.httpClient.delete<String>(this.API_USUARIO + "/" + id);
  }
}
