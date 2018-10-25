import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Response } from 'src/app/modelos/response';
import { Util } from 'src/app/util';

@Injectable({
  providedIn: 'root'
})
export class ProcessosService {

  constructor(public http: HttpClient) { }

  public deletar(id) {
    return this.http.delete<Response>(Util._url + 'api-processo/processo?id=' + id);
  }
  public cadastrar(processo) {
    return this.http.post<Response>(Util._url + 'api-processo/processo', processo);
  }
  public listarTodosProcessos() {
    return this.http.get<Response>(Util._url + 'api-processo/processos');
  }
  public listarProcessosSemParecer(){
    return this.http.get<Response>(Util._url+ 'api-processo/processos-sem-parecer');
  }
}
