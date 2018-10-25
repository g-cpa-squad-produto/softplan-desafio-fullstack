import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Usuario } from '../modelos/user';
import * as jwt_decode from "jwt-decode";
import { Util } from '../util';
import { Response } from '../modelos/response';
import { Utils } from 'ngx-bootstrap';


@Injectable()
export class AuthService {
  public loggedIn: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);

  get isLoggedIn() {
    return this.loggedIn.asObservable();
  }

  constructor(
    public router: Router,
    public http: HttpClient
  ) { }

  login(user: Usuario) {
    this.http.post<Response>(Util._url + 'api-publica/login', user).toPromise().then(
      data => {
        window.localStorage.clear();
        let token = data.data.token;
        let jwtDecode = jwt_decode(token);
        console.log(jwtDecode)
        window.localStorage.setItem(Util.ID_DO_USUARIO, jwtDecode.id_usuario)
        window.localStorage.setItem(Util.TIPO_DO_USUARIO, jwtDecode.role)
        window.localStorage.setItem(Util.NOME_DO_USUARIO, jwtDecode.nome)
        window.localStorage.setItem(Util.TOKEN, token);
        this.loggedIn.next(true);

        if (jwtDecode.role == Util.ROLE_USUARIO_FINALIZADOR) {
          this.router.navigate(['/parecer']);
          return;
        }
        if (jwtDecode.role == Util.ROLE_USUARIO_TRIADOR) {
          this.router.navigate(['/processos']);
          return;
        }
        if (jwtDecode.role == Util.ROLE_ADMINISTRADOR) {
          this.router.navigate(['/usuarios']);
          return;
        }

      }
    ).catch(
      error => {
        alert('Usuario ou senha incorretas');
      }
    )
  }
  isAuthenticated() {
    const token = localStorage.getItem(Util.TOKEN);
    if (!token) {
      console.log("Token nao existe");
      return false;
    }
    else {
      console.log("Token existe");
      return true;
    }
  }


  logout() {
    this.loggedIn.next(false);
    this.router.navigate(['/login']);
  }
}