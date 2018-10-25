import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';
import * as decode from 'jwt-decode';
import { Util } from '../util';
import { map, take } from 'rxjs/operators';
import { AuthService } from '../service/auth.service';
import { Observable } from 'rxjs';

@Injectable()
export class AuthGuard implements CanActivate {
  constructor(public auth: AuthService, public router: Router) { }
  canActivate(route: ActivatedRouteSnapshot): Observable<boolean> {
    let ehValido = false;
    let expectedRoleArray = route.data;
    expectedRoleArray = expectedRoleArray.expectedRole;

    const token = localStorage.getItem(Util.TOKEN);

    const tokenPayload = decode(token);

    let expectedRole = '';

    for (let i = 0; i < expectedRoleArray.length; i++) {
      if (expectedRoleArray[i] == tokenPayload.role) {
        expectedRole = tokenPayload.role;
      }
    }

    if (this.auth.isAuthenticated() && tokenPayload.role == expectedRole) {
      console.log("User permitted to access the route");
      ehValido = true;
    }
    //Verifica se esta logado para aparecer ou não o menu
    return this.auth.isLoggedIn.pipe(
      take(1),
      map((estaLogado: boolean) => {
        if ((window.localStorage.getItem('token') && ehValido) || estaLogado) {
          this.auth.loggedIn.next(true);
          return true;
        } else {
          this.router.navigate(['/login']);
          return false;
        }
      })
    );
  }
}
