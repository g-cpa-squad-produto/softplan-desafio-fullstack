import { Injectable } from '@angular/core';
import { CanActivate, Router, RouterStateSnapshot, ActivatedRouteSnapshot } from '@angular/router';
import { TokenService } from '../token/token.service';
import { UserService } from 'src/app/user/user.service';

@Injectable({
  providedIn: 'root'
})
export class GuardService implements CanActivate {

  constructor(private userService: UserService, private tokenService: TokenService, private router: Router) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    if (!this.userService.isLogged()) {
      this.tokenService.removeToken();
      this.router.navigate(['/login']);
      return false;
    } else if (route.data.role && !this.userService.hasRole(route.data.role)) {
      this.tokenService.removeToken();
      this.router.navigate(['/login']);
      return false;
    }

    return true;
  }
}