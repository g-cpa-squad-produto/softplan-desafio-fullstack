import { TokenService } from './../token/token.service';
import { HttpService } from './../http/http.service';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { User } from 'src/app/model/user';

const endPoint = 'users';

@Injectable({ providedIn: 'root' })
export class UserService {
  _user: User;

  constructor(
    private httpService: HttpService<User>,
    private tokenService: TokenService
  ) {}

  public findAll(): Observable<User[]> {
    return this.httpService.get(`${endPoint}`);
  }

  public findById(id: number) {
      return  this.httpService.getOne(`${endPoint}\/${id}`);
  }
  public delete(id: number): Observable<any> {
    return this.httpService.delete(`${endPoint}`, id);
  }

}
