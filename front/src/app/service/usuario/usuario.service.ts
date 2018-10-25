import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Usuario } from 'src/app/modelos/user';
import { Util } from 'src/app/util';
import { Response } from 'src/app/modelos/response';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  constructor(public http: HttpClient) { }

  getAllUser() {
    return this.http.get<Response>(Util._url + 'api-usuario/usuarios');
  }
  cadastrarUsuario(user: Usuario) {
    return this.http.post<Response>(Util._url + 'api-usuario/usuario', user);
  }
  deletarUsuario(id: number) {
    return this.http.delete<Response>(Util._url + 'api-usuario/usuario?id=' + id);
  }
}
