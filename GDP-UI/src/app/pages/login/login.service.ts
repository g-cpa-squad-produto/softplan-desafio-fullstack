import { HttpService } from './../../core/http/http.service';
import { Injectable } from '@angular/core';
import { TokenService } from 'src/app/core/token/token.service';
import { Router } from '@angular/router';

import { tap } from 'rxjs/operators';
import { User } from '../../model/user';
import { Observable } from 'rxjs';
import { MessegesService } from '../../core/messeges/messages.service';
import { HttpErrorResponse } from '@angular/common/http';
import { Token } from 'src/app/model/token';


@Injectable({ providedIn: 'root'})
export class LoginService {
  constructor(
    private httpService: HttpService<User>,
    private messageService: MessegesService,
    private tokenService: TokenService,
    private router: Router ) {

   }

   public isLoggedIn() {
     return this.tokenService.hasToken();
   }

   authenticate(user: User): Observable<Token> {
    return this.httpService.login(user, 'login/autenticate').pipe(
      tap(token => {
           this.tokenService.setToken(token.value);
           this.router.navigate(['/']);
      },  (error: HttpErrorResponse ) => {
        console.log(error);
      })
    );

  }
}
