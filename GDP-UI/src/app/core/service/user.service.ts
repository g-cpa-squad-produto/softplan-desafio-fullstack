import { TokenService } from './../token/token.service';
import { HttpService } from './../http/http.service';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { User } from 'src/app/model/user';
import { PerfilTypes } from 'src/app/model/perfil-types';
import { tap } from 'rxjs/operators';
import { HttpErrorResponse } from '@angular/common/http';

@Injectable({ providedIn: 'root'})
export class UserService {


    _user: User;


    constructor (
      private httpService: HttpService<User>,
      private tokenService: TokenService) {

    }

    public getUser(): Observable<User> {
      if (this.tokenService.hasToken()) {
        return this.httpService.post(this.tokenService.getUser(), 'login/user');
      }
    }

    public all ():  Observable<User[]> {
      return this.httpService.get('users/all');
    }

    delete(id: number): Observable<any> {
      return this.httpService.delete('users', id);
    }
  }
