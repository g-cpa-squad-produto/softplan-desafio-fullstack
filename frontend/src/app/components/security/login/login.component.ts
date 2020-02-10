import {Component, OnInit} from '@angular/core';
import {SharedService} from '../../../services/shared.service';
import {UserService} from '../../../services/user.service';
import {Router} from '@angular/router';
import {Login} from '../../../shared/model/login.model';
import {User} from '../../../shared/model/user.model';

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
    private shared: SharedService
  ) {
    if (shared.isLoogegIn()) {
      this.router.navigate(['/dashboard']);
    }
  }

  ngOnInit() {
  }

  login(): void {
    this.message = '';

    this.userService.login(this.loginUser)
      .subscribe(
        (user: User) => {
          this.message = '';
          this.shared.showTemplate.emit(true);
          this.shared.storeUserName(`${user.firstName} ${user.lastName}`);
          this.router.navigate(['/dashboard']);
          this.userService.currentUserEmmiter.emit(user);
        },
        () => (this.message = 'Login ou senha inválido')
      );
  }
}
