import { Injectable } from '@angular/core';
import { CanActivate, Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';

import { LoginService } from 'src/app/pages/login/login.service';
import { ProfileTypes } from 'src/app/model/profile-types';
import { MassegesService } from './../../core/messeges/messages.service';
import { TokenService } from 'src/app/core/token/token.service';

@Injectable({ providedIn: 'root'})
export class FinalizeGuard implements CanActivate {

  constructor(private loginService: LoginService,
              private tokenService: TokenService,
              private massegeService: MassegesService,
              private router: Router) {}

  public canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    if (this.loginService.getUserProfile() === ProfileTypes.FINALIZADOR) {
      return true;
    }
    this.router.navigate(['login']);
    this.massegeService.error('Realize login novamente', 'Acesso negado');
    this.tokenService.removeToken();
    return false;
  }
}
