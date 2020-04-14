import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { AuthenticationService } from '../services/authentication.service';



@Injectable()
export class AdminGuard implements CanActivate {
    constructor(
        private authService: AuthenticationService,
        private router: Router
    ) { }

    async canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Promise<boolean> {
        const currentUser = await this.authService.getUser();
        console.log(currentUser);
        if (!currentUser.isAdmin()) {
            this.router.navigate(['login']);
            return false;
        }

        return true;
    }
}
