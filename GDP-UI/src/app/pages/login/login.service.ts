import { Injectable } from '@angular/core';
import { TokenService } from 'src/app/core/token/token.service';
import { HttpClient } from '@angular/common/http';

import { tap } from 'rxjs/operators';

const API_URL = 'http://localhost:8080';

@Injectable({ providedIn: 'root'})
export class LoginService {



  constructor(
    private http: HttpClient,
    private tokenService: TokenService) {

   }

   public isLoggedIn() {
     return this.tokenService.hasToken();
   }

   authenticate(userName: string, password: string) {

    return this.http
      .post(
        API_URL + '/user/login',
        { userName, password },
        { observe: 'response'}
      )
      .pipe(tap(res => {
        const authToken = res.headers.get('x-access-token');
        this.tokenService.setToken(authToken);
        console.log(`User ${userName} authenticated with token ${authToken}`);
      }));
  }
}
