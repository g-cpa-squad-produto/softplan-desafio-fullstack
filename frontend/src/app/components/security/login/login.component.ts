import {Component, OnInit} from '@angular/core';
import {SharedService} from '../../../services/shared.service';
import {UserService} from '../../../services/user.service';
import {Router} from '@angular/router';
import {Login} from '../../../shared/model/login.model';
import {Observable} from 'rxjs';
import {User} from '../../../shared/model/user.model';
import {LocalStorageService} from 'ngx-webstorage';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit {

  loginUser = new Login();
  message: string;

  constructor(
    private userService: UserService,
    private router: Router,
    private shared: SharedService,
    private $localStorage: LocalStorageService
  ) { }

  ngOnInit() {
  }

  login(): void {
    this.message = '';

    this.userService.login(this.loginUser)
      .subscribe(
        (user: User) => {
          this.message = ''
          this.shared.showTemplate.emit(true);

          this.router.navigate(['/dashboard']);
        },
        () => (this.message = 'Login ou senha inválido')
      );

    // this.loginService.login(this.loginUser)
    //   .pipe(flatMap(() => this.loginService.identity()));
  }

  logout(): Observable<void> {
    return new Observable(observer => {
      this.$localStorage.clear('authenticationToken');
      observer.complete();
    });
  }
}
