import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {RealizarConsultaApiService} from '../../shared/service/realizar-consulta-api.service';
import {Observable} from 'rxjs';
import {Enum} from '../../shared/model/enum';
import {shareReplay} from 'rxjs/internal/operators';
import {UsuarioResumido} from '../model/usuario-resumido';
import {Usuario} from '../model/usuario';

@Injectable({
  providedIn: 'root'
})
export class UsuariosService extends RealizarConsultaApiService {

  private perfisCache$: Observable<Enum[]>;

  constructor(protected http: HttpClient) {
    super(http, '/usuarios/');
  }

  inativarUsuario(id: number): Observable<Boolean> {
    return this.put<Boolean>(null, `${id}/bloquear`);
  }

  ativarUsuario(id: number): Observable<Boolean> {
    return this.put<Boolean>(null, `${id}/desbloquear`);
  }

  pesquisarUsuariosTelaLogin(): Observable<Usuario[]> {
    return this.get<Usuario[]>('lista-login');
  }

  pesquisarUsuariosFinalizadores(): Observable<UsuarioResumido[]> {
    return this.get<UsuarioResumido[]>('finalizadores');
  }

  pesquisarPerfis(): Observable<Enum[]> {
    if (!this.perfisCache$) {
      this.perfisCache$ = this.get<Enum[]>('perfis').pipe(shareReplay(1));
    }
    return this.perfisCache$;
  }
}
