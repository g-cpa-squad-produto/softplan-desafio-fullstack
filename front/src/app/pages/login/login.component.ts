import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login/login.service';
import { Usuario } from 'src/app/modelos/usuario';
import * as jwt_decode from "jwt-decode";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [LoginService]
})
export class LoginComponent implements OnInit {

  constructor(private loginService: LoginService, private router: Router) { }
  netImage: any = "assets/images/a.png";
  user: Usuario = new Usuario();
  LogOn() {
    this.loginService.login(this.user).then(
      data => {
        let token = data.data.token;
        let jwtDecode = jwt_decode(token);
        console.log(jwtDecode)
        window.localStorage.setItem('idUsuario', jwtDecode.id)
        window.localStorage.setItem('token', token);
        this.router.navigate(['home']);
      }
    ).catch(
      error => {
        alert('Usuario ou senha invalidos')
        this.user = new Usuario();
      }
    )
  }

  ngOnInit() {
  }

}

