import { Injectable } from '@angular/core';
import { User } from 'src/app/model/user';
import { Token } from 'src/app/model/token';

const KEY = 'authToken';

@Injectable({ providedIn: 'root'})
export class TokenService {
  getUser(): User {
    return JSON.parse(window.localStorage.getItem('USER')) as User;
  }

  hasToken() {
        return !!this.getToken();
    }

    setToken(token: Token) {
        window.localStorage.setItem(KEY, (JSON.stringify(token)));
    }

    setUser(user: User) {
      user.password = null;
      console.log(user);
      window.localStorage.setItem('USER', (JSON.stringify(user)));
    }

    getToken() {
        return window.localStorage.getItem(KEY);
    }

    removeToken() {
        window.localStorage.removeItem(KEY);
    }
}
