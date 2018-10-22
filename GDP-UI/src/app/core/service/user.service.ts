import { HttpService } from './../http/http.service';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { User } from 'src/app/model/user';
import { PerfilTypes } from 'src/app/model/perfil-types';
import { tap } from 'rxjs/operators';
import { HttpErrorResponse } from '@angular/common/http';

@Injectable({ providedIn: 'root'})
export class UserService {

    user: User;

    constructor (private httpService: HttpService<User>) {

    }

    public getUser(): Observable<User> {
      if (this.user) {
        return this.httpService.post(this.user, 'login/user').pipe(
          tap(user => {
             this.user = user;
          })
        );
      }

    }

      public setUser(user: User): void {
        this.user = user;
      }

  }
