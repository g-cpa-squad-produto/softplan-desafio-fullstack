import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material';

import {Login } from '../../models';
import{LoginService} from '../../services';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  form: FormGroup;

  constructor(
    private fb: FormBuilder,
    private snackBar: MatSnackBar,
    private router: Router,
    private loginService: LoginService
  ) { }

  ngOnInit() {
    this.gerarForm();
  }

  gerarForm() {
    this.form = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      senha: ['', [Validators.required, Validators.minLength(5)]]
    });
  }

  logar() {
    if (this.form.invalid) {
      this.snackBar.open(
      "Dados Inválidos", "Error", {duration:5000}  
      );
      return;
    }
    const login: Login = this.form.value; 
    this.loginService.logar(login)
      .subscribe(
        data => {
          console.log(data);
          localStorage['token'] = data['token'];
          const authorities = data['authorities'];
          /*const usuarioData = JSON.parse(
            atob(data['token'].split('.')[1]));*/
          if (authorities == '[ROLE_ADMIN]') {
            alert ("redirecionar para tela de ADM. perfil:"+ authorities)
            //this.router.navigate(['/admin']);
          } else if(authorities== '[ROLE_TRIADOR]'){
            alert ("redirecionar para tela de Triador. perfil:"+ authorities)
            //this.router.navigate(['/funcionario']);
          }else{
            alert ("redirecionar para tela de Finalizador. perfil:"+ authorities)
            //this.router.navigate(['/funcionario']);
          }
        },
        err => {
          let msg: string = "Tente novamente em instantes.";
          if (err['status'] == 401) {
            msg = "Email/senha inválido(s)."
          }
          this.snackBar.open(msg, "Erro", { duration: 5000 });
        }
      );
  }
}
