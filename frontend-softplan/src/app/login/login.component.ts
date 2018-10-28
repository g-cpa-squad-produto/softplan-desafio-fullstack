import { Component, OnInit } from '@angular/core';
import { Usuario } from '../usuario/usuario.entity';
import { SharedService } from './shared.service';
import { UsuarioService } from '../usuario/usuario.service';
import { Router } from '@angular/router';
import { CurrentUser } from '../usuario/currentuser.entity';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  usuario = new Usuario();
  sharedService: SharedService;
  message: String;
  constructor(private usuarioService: UsuarioService,
              private router: Router) {
      this.sharedService = SharedService.getInstance();
  }

  ngOnInit() {
  }

  login() {
    this.message = '';
    this.usuarioService.login(this.usuario).subscribe((userAuthentication: CurrentUser) => {
      this.sharedService.token = userAuthentication.token;
      this.sharedService.usuario = userAuthentication.usuario;
      this.sharedService.usuario.perfil = this.sharedService.usuario.perfil.substring(5);
      this.sharedService.showTemplate.emit(true);
      this.router.navigate(['/home']);
    }, err => {
      this.sharedService.token = null;
      this.sharedService.usuario = null;
      this.sharedService.showTemplate.emit(false);
      this.message = 'Erro';
    });
  }

  cancelLogin() {
    this.message = '';
    this.usuario = new Usuario();
    window.location.href = '/login';
    window.location.reload();
  }
}
