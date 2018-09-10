import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve} from '@angular/router';
import {Observable} from 'rxjs';
import {Usuario} from '../model/usuario';
import {UsuariosService} from '../service/usuarios.service';

@Injectable({
  providedIn: 'root'
})
export class ConsultarUsuarioResolve implements Resolve<Usuario> {

  constructor(private service: UsuariosService) {
  }

  resolve(route: ActivatedRouteSnapshot): Observable<Usuario> {
    const idUsuario = route.paramMap.get('idUsuario');
    return this.service.pesquisarPorId(idUsuario);
  }

}
