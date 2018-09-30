import { CurrentUser } from './../../../model/currentUser';
import { SharedService } from './../../../services/shared.service';
import { UserService } from './../../../services/user/user.service';
import { User } from './../../../model/user';
import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user = new User('','','','');
  shared : SharedService;
  message : string;

  constructor(private userService: UserService,
              private router: Router) { 
    this.shared = SharedService.getInstance();
  }

  ngOnInit() {
  }
  
  /**Realiza o login */
  login(){
    this.message = '';
    this.userService.login(this.user).subscribe((userAuthentication:CurrentUser) => {
        this.shared.token = userAuthentication.token;
        this.shared.user = userAuthentication.user;
        this.shared.user.profile = this.shared.user.profile.substring(5);
        this.shared.showTemplate.emit(true);
        this.router.navigate(['/']);
    } , err => {
      this.shared.token = null;
      this.shared.user = null;
      this.shared.showTemplate.emit(false);
      this.message = 'Erro ';
    });
  }

  cancelLogin(){
    this.message = '';
    this.user = new User('', '','','');
    window.location.href = '/login';
    window.location.reload();
  }

  /**Retorna a classe css de acordo com a validação */
  getFormGroupClass(isInvalid: boolean, isDirty:boolean): {} {
    return {
      'form-group': true,
      'has-error' : isInvalid  && isDirty,
      'has-success' : !isInvalid  && isDirty
    };
  }

}
