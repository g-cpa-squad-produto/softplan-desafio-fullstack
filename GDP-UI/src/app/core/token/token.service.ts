import { Injectable } from '@angular/core';
import { UserDTO } from 'src/app/model/user.dto';

const KEY = 'authToken';

@Injectable({ providedIn: 'root'})
export class TokenService {

  hasToken() {
        return !!this.getToken();
    }

    setTokenUserDTO(userDTO: UserDTO) {
        window.localStorage.setItem(KEY, (JSON.stringify(userDTO)));
    }

    getTokenUserDTO(): UserDTO {
      return JSON.parse(window.localStorage.getItem(KEY)) as UserDTO;
    }


    getToken() {
        return window.localStorage.getItem(KEY);
    }

    removeToken() {
        window.localStorage.removeItem(KEY);
    }
}
