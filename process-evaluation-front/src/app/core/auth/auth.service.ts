import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { tap } from 'rxjs/operators';
import { TokenService } from '../token/token.service';
import { UserService } from 'src/app/user/user.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient, private tokenService: TokenService, private userService: UserService) { }

  authenticate(username, password) {
    const url = `${environment.api}/auth`
    return this.http.post(url, {username, password}, {observe: 'response'})
      .pipe(tap(res => {
        const token = res.headers.get('Authorization');
        this.tokenService.setToken(token);
        this.userService.decodeAndNotify();
      }));
  }  
}