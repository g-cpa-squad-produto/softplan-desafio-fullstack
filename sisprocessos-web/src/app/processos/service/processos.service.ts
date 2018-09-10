import {Injectable} from '@angular/core';
import {RealizarConsultaApiService} from '../../shared/service/realizar-consulta-api.service';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Enum} from '../../shared/model/enum';
import {shareReplay} from 'rxjs/internal/operators';
import {Processo} from '../model/processo';

@Injectable({
  providedIn: 'root'
})
export class ProcessosService extends RealizarConsultaApiService {

  private statusCache$: Observable<Enum[]>;

  constructor(protected http: HttpClient) {
    super(http, '/processos/');
  }

  pesquisarStatus(): Observable<Enum[]> {
    if (!this.statusCache$) {
      this.statusCache$ = this.get<Enum[]>('status').pipe(shareReplay(1));
    }
    return this.statusCache$;
  }

  atribuirUsuariosAProcesso(processo: Processo): Observable<any> {
    const ids = processo.usuariosPermissao.map(value => value.id);
    return this.put(ids, `atribuir-usuarios/${processo.id}`);
  }

  incluirParecer(processo: Processo): Observable<any> {
    return this.put({textoParecer: processo.parecer}, `incluir-parecer/${processo.id}`);
  }

}
