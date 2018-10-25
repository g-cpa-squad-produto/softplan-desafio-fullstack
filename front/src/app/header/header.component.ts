import { Observable } from 'rxjs';
import { Component, OnInit } from '@angular/core';
import { AuthService } from '../service/auth.service';
import { Util } from '../util';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styles: [
    `.angular-logo {
        margin: 0 4px 3px 0;
        height: 35px;
        vertical-align: middle;
    }
    .fill-remaining-space {
      flex: 1 1 auto;
    }
    `
  ]
})
export class HeaderComponent implements OnInit {
  public nomeDoUsuario: string;
  isLoggedIn$: Observable<boolean>;

  constructor(
    private router: Router,
    private authService: AuthService) {
    this.nomeDoUsuario = window.localStorage.getItem(Util.NOME_DO_USUARIO)
  }

  ngOnInit() {
    this.isLoggedIn$ = this.authService.isLoggedIn;
  }
  redirecionar(url) {
    this.router.navigate([url]);
  }

  onLogout() {
    this.authService.logout();
  }

}