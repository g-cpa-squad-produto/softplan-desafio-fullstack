import { Router } from '@angular/router';
import { UsuarioService } from './../../servicos/usuario.service';
import { Usuario } from './../../entidades/usuario';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  usuario: Usuario;

  constructor(
    private _usarioService: UsuarioService,
    private _router: Router
  ) { }

  ngOnInit() {
    this.usuario = new Usuario();
  }

  login() {
    this._usarioService.login(this.usuario).subscribe(
      res => {console.log(res);
        localStorage.setItem('token', res);
        this._router.navigate(['/usuario']);
      }
    );
  }

}
