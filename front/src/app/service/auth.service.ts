import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Usuario } from '../modelos/user';
import * as jwt_decode from "jwt-decode";
import { Util } from '../util';
import { Response } from '../modelos/response';


@Injectable()
export class AuthService {
  public loggedIn: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);

  get isLoggedIn() {
    return this.loggedIn.asObservable();
  }

  constructor(
    private router: Router,
    private http: HttpClient
  ) { }

  login(user: Usuario) {
    this.http.post<Response>(Util._url + 'api-publica/login', user).toPromise().then(
      data => {
        let token = data.data.token;
        let jwtDecode = jwt_decode(token);
        console.log(jwtDecode)
        window.localStorage.setItem(Util.ID_DO_USUARIO, jwtDecode.id_usuario)
        window.localStorage.setItem(Util.TIPO_DO_USUARIO, jwtDecode.role)
        window.localStorage.setItem(Util.NOME_DO_USUARIO, jwtDecode.nome)
        window.localStorage.setItem(Util.TOKEN, token);
        this.loggedIn.next(true);
        this.router.navigate(['/usuarios']);
      }
    ).catch(
      error => {
        alert('Usuario ou senha incorretas');
      }
    )
  }

  logout() {
    this.loggedIn.next(false);
    this.router.navigate(['/login']);
  }
}