import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Correio } from './correio.model';

@Injectable({
  providedIn: 'root'
})
export class CorreioService {

  constructor(
    private http: HttpClient
  ) { }

  getEnderecoPeloCep(cep) {
    return this.http.get<Correio>(`https://viacep.com.br/ws/01001000/json/${cep}`)
  }
}
