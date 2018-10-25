import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Parecer } from 'src/app/modelos/parecer';
import { Util } from 'src/app/util';
import { Response } from 'src/app/modelos/response';

@Injectable({
  providedIn: 'root'
})
export class ParecerService {

  constructor(public http: HttpClient) { }

  public cadastrar(parecer: Parecer) {
    return this.http.post<Response>(Util._url + 'api-parecer/parecer', parecer);
  }
  public listarParecerDoProcesso(idProcesso) {
    return this.http.get<Response>(Util._url + 'api-parecer/parecer?idProcesso=' + idProcesso);
  }

}
