import { JwtHelperService } from '@auth0/angular-jwt';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor( public jwtHelper: JwtHelperService ) {}

  public isAuthenticated(): boolean {
    const token = localStorage.getItem('token');
    // Valida se token é valido
    //return !this.jwtHelper.isTokenExpired(token);
    if (token !== undefined && token !== null) {
      this.jwtHelper.decodeToken(token);
      return true;
    } else {
      return false;
    }
  }
}
