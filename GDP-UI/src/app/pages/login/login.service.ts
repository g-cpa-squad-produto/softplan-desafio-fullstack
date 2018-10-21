import { HttpService } from './../../core/http/http.service';
import { Injectable } from '@angular/core';
import { TokenService } from 'src/app/core/token/token.service';
import { Router } from '@angular/router';

import { tap } from 'rxjs/operators';
import { User } from '../../model/user';
import { Observable } from 'rxjs';
import { MessegesService } from '../../core/messeges/messages.service';
import { HttpErrorResponse } from '@angular/common/http';


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

   authenticate(user: User): Observable<User> {
    return this.httpService.post(user, 'login/autenticate').pipe(
      tap(res => {
           this.tokenService.setToken(user);
           this.router.navigate(['/']);
      },  (error: HttpErrorResponse ) => {
        console.log(error);
      })
    );

  }
}
