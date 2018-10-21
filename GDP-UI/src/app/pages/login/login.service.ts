import { Injectable } from '@angular/core';
import { TokenService } from 'src/app/core/token/token.service';

@Injectable({ providedIn: 'root'})
export class LoginService {

  constructor(private tokenService: TokenService) {

   }

   public isLoggedIn() {
     return this.tokenService.hasToken();
   }

   public login() {
    this.tokenService.setToken('YURE');
   }
}
