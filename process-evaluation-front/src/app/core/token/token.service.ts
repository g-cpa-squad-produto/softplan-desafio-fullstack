import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';

const KEY = 'process-evaluation';

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  private jwtHelper: JwtHelperService;

  constructor() {
    this.jwtHelper = new JwtHelperService();
  }

  setToken(token) {
    localStorage.setItem(KEY, token);
  }

  getToken(): string {
    return localStorage.getItem(KEY);
  }

  hasToken() {
    return this.getToken();
  }

  isTokenExpired() {
    const token = this.getToken();
    return token && this.jwtHelper.isTokenExpired(token);
  }  

  removeToken() {
    if (this.getToken()) {
      localStorage.removeItem(KEY);
    }
  }

  private getPayload() {
    const payload = JSON.stringify(this.jwtHelper.decodeToken(this.getToken()));
    return JSON.parse(payload);
  }

  getAuthority(): string {
    if (!this.getPayload()) {
      return;
    }
    return this.getPayload().authorities[0];
  }

  getUserName(): string {
    if (!this.getPayload()) {
      return;
    }
    return this.getPayload().userName;
  }
}