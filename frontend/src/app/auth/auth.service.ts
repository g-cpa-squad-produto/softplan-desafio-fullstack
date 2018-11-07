import { JwtHelperService } from '@auth0/angular-jwt';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor( public jwtHelper: JwtHelperService ) {}

  public isAuthenticated(): boolean {
    /* const token = localStorage.getItem('token');
    console.log(token); */
    // Valida se token é valido
    return true;//!this.jwtHelper.isTokenExpired(token);
  }
}
