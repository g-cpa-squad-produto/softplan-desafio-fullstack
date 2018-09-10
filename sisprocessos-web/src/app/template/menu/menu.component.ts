import {Component, OnInit} from '@angular/core';
import {AuthService} from '../../shared/auth/auth.service';
import {Usuario} from '../../usuarios/model/usuario';
import {Router} from '@angular/router';

@Component({
  selector: 'menu-template',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  usuario: Usuario;

  constructor(private authService: AuthService, private router: Router) {
  }

  ngOnInit(): void {
    this.usuario = this.authService.getUsuarioAutenticado();
  }

  logout() {
    this.authService.logout();
    location.reload();
  }

}
