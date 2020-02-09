import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {Injectable} from '@angular/core';
import {SharedService} from '../../services/shared.service';
import {Observable} from 'rxjs';

@Injectable()
export class AuthGuard implements CanActivate {

  constructor(
    private router: Router,
    private shared: SharedService) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | boolean {
    if ( this.shared.isLoogegIn() ) {
      return true;
    }
    this.router.navigate(['/']);
    return false;
  }


}
