import { Component, OnInit } from '@angular/core';
import {SharedService} from "../../../services/shared.service";
import {UserService} from "../../../services/user.service";
import {Router} from "@angular/router";
import {Login} from "../../../shared/model/login.model";
import {Observable} from "rxjs";
import {LocalStorageService} from "ngx-webstorage";
import {map} from "rxjs/operators";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginUser = new Login();
  shared: SharedService;
  message: string;

  constructor(
    private userService: UserService,
    private router: Router,
    private $localStorage: LocalStorageService
  ) {
    this.shared = SharedService.getInstance();
  }

  ngOnInit() {
  }

  login(){
    this.message = '';

    // this.userService.login(this.loginUser)
    //   .subscribe(response => {
    //     const jwt = response.id_token;
    //   });
    //
    // this.userService.fetch()
    //   .subscribe(response => {
    //     console.log(response)
    //   });

    this.userService.login(this.loginUser)
      .subscribe(
        () => {
          this.message = ''
          if (
            this.router.url === '/account/register' ||
            this.router.url.startsWith('/account/activate') ||
            this.router.url.startsWith('/account/reset/')
          ) {
            this.router.navigate(['']);
          }
        },
        () => (this.message = 'Login ou senha inválido')
      );
  }

  logout(): Observable<void> {
    return new Observable(observer => {
      this.$localStorage.clear('authenticationToken');
      observer.complete();
    });
  }
}
