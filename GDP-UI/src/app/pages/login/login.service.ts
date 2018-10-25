import { HttpService } from './../../core/http/http.service';
import { Injectable } from '@angular/core';
import { TokenService } from 'src/app/core/token/token.service';
import { Router } from '@angular/router';

import { tap } from 'rxjs/operators';
import { User } from '../../model/user';
import { Observable } from 'rxjs';
import { HttpErrorResponse } from '@angular/common/http';
import { UserDTO } from 'src/app/model/user.dto';
import { UserService } from 'src/app/core/service/user.service';
import { ProfileTypes } from 'src/app/model/profile-types';


@Injectable({ providedIn: 'root'})
export class LoginService {
  constructor(
    private httpService: HttpService<User>,
    private tokenService: TokenService,
    private router: Router ) { }

   public isLoggedIn() {
     return this.tokenService.hasToken();
   }

   public decidirRouter() {

      const userDTO = this.tokenService.getTokenUserDTO();
      if (userDTO.profile === ProfileTypes.ADMIN) {
          this.router.navigate(['/usuarios']);
      }
      if (userDTO.profile === ProfileTypes.FINALIZADOR) {
          this.router.navigate(['/parecer']);
      }
      if (userDTO.profile === ProfileTypes.TRIADADOR) {
        this.router.navigate(['/triagem']);
      }
   }

  public authenticate(user: User): Observable<UserDTO> {
    return this.httpService.login(user, 'login/autenticate').pipe(
      tap((userDTO: UserDTO) => {
           this.tokenService.setTokenUserDTO(userDTO);
           this.decidirRouter();
      },  (error: HttpErrorResponse ) => {
        console.log(error);
      })
    );
  }
}
