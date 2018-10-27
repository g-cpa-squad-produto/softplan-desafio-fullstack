import { HttpService } from './../http/http.service';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { User } from 'src/app/model/user';
import { GenericicService } from './generic.service';

const endPoint = 'users';

@Injectable({ providedIn: 'root' })
export class UserService extends GenericicService<User> {

  constructor(httpService: HttpService<User>) {
    super(endPoint, httpService);
  }

}
