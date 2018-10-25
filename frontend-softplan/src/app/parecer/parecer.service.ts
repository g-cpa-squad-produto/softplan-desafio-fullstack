import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Processo } from '../processo/processo.entity';
import { GenericServerService } from '../generic-server.service';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ParecerService extends GenericServerService {

  private api: string;
  constructor(private http: HttpClient) {
    super();
    this.api = this.getUrl() + '/pareceres/';
  }

  salvar(processo: Processo) {
    return this.http.post(this.api, JSON.stringify(processo), this.httpOptions)
      .pipe(map(res => res));
  }

  listProcessosSemParecer(): Observable<any> {
    return this.http.get<any>(this.api, this.httpOptions).pipe(map(res => res));
  }

  getProcessoById(processoId: String): Observable<Processo> {
    return this.http.get<Processo>(`${this.api}incluir/${processoId}`,
            this.httpOptions).pipe(map(res => res));
  }
}
