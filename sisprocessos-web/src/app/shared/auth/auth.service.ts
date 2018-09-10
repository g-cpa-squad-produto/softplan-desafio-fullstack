import {Injectable} from '@angular/core';
import {Usuario} from '../../usuarios/model/usuario';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  isAutenticado(): boolean {
    return this.getUsuarioAutenticado() != null;
  }

  getUsuarioAutenticado(): Usuario {
    return JSON.parse(localStorage.getItem('usuarioLogado'));
  }

  getToken(): string {
    return btoa(`${this.getUsuarioAutenticado().login}:${this.getUsuarioAutenticado().senha}`);
  }

  login(usuario: Usuario): void {
    localStorage.setItem('usuarioLogado', JSON.stringify(usuario));
  }

  logout(): void {
    localStorage.removeItem('usuarioLogado');
  }

}
