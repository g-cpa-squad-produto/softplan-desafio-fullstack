import {Injectable} from '@angular/core';
import {RealizarConsultaApiService} from '../../shared/service/realizar-consulta-api.service';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Enum} from '../../shared/model/enum';
import {shareReplay} from 'rxjs/internal/operators';

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
}
