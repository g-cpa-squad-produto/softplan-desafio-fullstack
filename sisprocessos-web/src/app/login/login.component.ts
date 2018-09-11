import {Component, OnInit} from '@angular/core';
import {UsuariosService} from '../usuarios/service/usuarios.service';
import {Observable} from 'rxjs';
import {Usuario} from '../usuarios/model/usuario';
import {AuthService} from '../shared/auth/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  usuarios$: Observable<Usuario[]>;
  usuarioSelecionado: Usuario;

  constructor(private usuariosService: UsuariosService,
              private authService: AuthService) {
  }

  ngOnInit() {
    this.usuarios$ = this.usuariosService.pesquisarUsuariosTelaLogin();
  }

  onChange(usuario: Usuario) {
    this.usuarioSelecionado = usuario;
  }

  login() {
    this.authService.login(this.usuarioSelecionado);
    location.href = '/';
  }

}
