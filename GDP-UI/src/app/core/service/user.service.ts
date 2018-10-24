import { TokenService } from './../token/token.service';
import { HttpService } from './../http/http.service';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { User } from 'src/app/model/user';

@Injectable({ providedIn: 'root' })
export class UserService {
  _user: User;

  constructor(
    private httpService: HttpService<User>,
    private tokenService: TokenService
  ) {}

  public findAll(): Observable<User[]> {
    return this.httpService.get('users');
  }

  public delete(id: number): Observable<any> {
    return this.httpService.delete('users', id);
  }
}
