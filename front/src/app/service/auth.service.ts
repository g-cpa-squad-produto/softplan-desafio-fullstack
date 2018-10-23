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
        window.localStorage.setItem('idUsuario', jwtDecode.id)
        window.localStorage.setItem('token', token);
        this.router.navigate(['home']);
        this.loggedIn.next(true);
        this.router.navigate(['/']);
      }
    )
  }

  logout() {
    this.loggedIn.next(false);
    this.router.navigate(['/login']);
  }
}