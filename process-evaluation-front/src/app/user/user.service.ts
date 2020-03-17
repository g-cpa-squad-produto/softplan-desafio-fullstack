import { Injectable } from '@angular/core';
import { TokenService } from '../core/token/token.service';
import { BehaviorSubject } from 'rxjs';
import { HttpClient, HttpParams } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { User } from './user';
import { Page } from '../shared/page';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private userLogged = new BehaviorSubject<String>(null);

  constructor(private tokenService: TokenService, private http: HttpClient) {
    if (this.tokenService.hasToken()) {
      this.decodeAndNotify();
    }
  }

  isLogged(): boolean {
    return this.tokenService.hasToken() && !this.tokenService.isTokenExpired();
  }

  hasRole(role: string): boolean {
    return this.tokenService.getAuthority().indexOf(role) !== -1;
  }

  getUser() {
    return this.userLogged.asObservable();
  }
  
  decodeAndNotify() {
    this.userLogged.next(this.tokenService.getUserName());
  }

  logout() {
    this.tokenService.removeToken();
    this.userLogged.next(null);
  }

  isAdmin() {
    if (!this.tokenService.getAuthority()) {
      return false;
    }

    return this.tokenService.getAuthority().indexOf('ROLE_ADMIN') !== -1;
  }

  isScreening() {
    if (!this.tokenService.getAuthority()) {
      return false;
    }

    return this.tokenService.getAuthority().indexOf('ROLE_SCREENING') !== -1;
  }

  isCloser() {
    if (!this.tokenService.getAuthority()) {
      return false;
    }

    return this.tokenService.getAuthority().indexOf('ROLE_CLOSER') !== -1;
  }

  findAll(page: number, size: number) {
    const url = `${environment.api}/users`;

    let params = new HttpParams();
    params = params.append('page', page.toString());
    params = params.append('size', size.toString());

    return this.http.get<Page<User>>(url, {params});
  }

  insert(user: User) {
    const url = `${environment.api}/users`;
    return this.http.post<User>(url, user);
  }

  findById(idUser: number) {
    const url = `${environment.api}/users/${idUser}`;
    return this.http.get<User>(url);
  }

  delete(idUser: number) {
    const url = `${environment.api}/users/${idUser}`;
    return this.http.delete(url);
  }

  findClosers() {
    const url = `${environment.api}/users/closers`;
    return this.http.get<User[]>(url);
  }
}