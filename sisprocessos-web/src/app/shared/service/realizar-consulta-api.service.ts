import {HttpClient, HttpParams} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {ApiHttpService} from './api-http.service';
import {RetornoConsultaApi} from '../model/retorno-consulta-api.model';

@Injectable()
export class RealizarConsultaApiService extends ApiHttpService {

  constructor(protected http: HttpClient, public basePath: string) {
    super(http, basePath);
  }

  pesquisarTodos<T>(): Observable<Array<T>> {
    return this.get<Array<T>>();
  }

  pesquisarPorId(id: string | number): Observable<any> {
    return this.get(id);
  }

  cadastrar(objeto: any): Observable<any> {
    return this.post(objeto);
  }

  editar(objeto: any): Observable<any> {
    return this.put(objeto);
  }

  deletar(id: any): Observable<any> {
    return this.delete(id);
  }

  filtrar<T>(filtro: any, page: string, size: string, sort: string = ''): Observable<RetornoConsultaApi<T>> {
    let params = new HttpParams();
    params = params.append('page', page);
    params = params.append('size', size);
    params = params.append('sort', sort);
    return this.realizarRequisicaoFiltro(filtro, params);
  }

  private realizarRequisicaoFiltro<T>(filtro: any, params: HttpParams): Observable<RetornoConsultaApi<T>> {
    return this.http.post<RetornoConsultaApi<T>>(this.getUrlServico() + 'filtro', filtro, {params: params});
  }

}
