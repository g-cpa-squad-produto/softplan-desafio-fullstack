import { Injectable } from '@angular/core';
import { CanActivate, Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';

import { LoginService } from 'src/app/pages/login/login.service';
import { MassegesService } from './../../core/messeges/messages.service';
import { TokenService } from 'src/app/core/token/token.service';

@Injectable({ providedIn: 'root'})
export class LoginGuard implements CanActivate {

  constructor(private loginService: LoginService,
              private massegeService: MassegesService) {}

  public canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    if (!this.loginService.isLoggedIn()) {
      return true;
    }
    this.massegeService.warning('Você já está logado', 'Aviso');
    return false;
  }
}
