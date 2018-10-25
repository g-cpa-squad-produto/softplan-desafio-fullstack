import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../service/auth.service';
import { Usuario } from '../modelos/user';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  constructor(public authService: AuthService) {
    window.localStorage.clear();
  }
  user: Usuario = new Usuario()
  ngOnInit() {
  }
  onSubmit() {
    this.authService.login(this.user);
  }
}