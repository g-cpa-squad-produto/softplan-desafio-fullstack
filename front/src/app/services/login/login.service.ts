import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Util } from '../Util';
import { Usuario } from 'src/app/modelos/usuario';
import { Response } from 'src/app/modelos/response';
@Injectable({
  providedIn: 'root'
})
export class LoginService {
  constructor(private client: HttpClient) {
  }

  login(user: Usuario) {
    return this.client.post<Response>(Util._url + 'api-publica/login', user).toPromise();
  }
}
