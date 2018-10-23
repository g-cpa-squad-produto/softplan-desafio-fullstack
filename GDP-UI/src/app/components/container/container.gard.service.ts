import { User } from './../../model/user';
import { TokenService } from 'src/app/core/token/token.service';
import { Injectable } from '@angular/core';
import {
  CanActivate,
  Router,
  ActivatedRouteSnapshot,
  RouterStateSnapshot
} from '@angular/router';
import { PerfilTypes } from 'src/app/model/perfil-types';


@Injectable({ providedIn: 'root'})
export class ContainerGuard implements CanActivate {

  constructor(private tokenService: TokenService, private router: Router) {}

  canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const user = new User (this.tokenService.getUser());

    if (user.profile === PerfilTypes.ADMIN) {
          this.router.navigate(['users']);
        }

        return true;
  }
}
