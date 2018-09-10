import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve} from '@angular/router';
import {Observable} from 'rxjs';
import {Processo} from '../model/processo';
import {ProcessosService} from '../service/processos.service';

@Injectable({
  providedIn: 'root'
})
export class ConsultarProcessoResolve implements Resolve<Processo> {

  constructor(private service: ProcessosService) {
  }

  resolve(route: ActivatedRouteSnapshot): Observable<Processo> {
    const idProcesso = route.paramMap.get('idProcesso');
    return this.service.pesquisarPorId(idProcesso);
  }

}
