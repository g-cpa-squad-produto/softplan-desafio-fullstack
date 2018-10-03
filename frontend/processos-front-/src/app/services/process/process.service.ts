import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { PROCESSOS_API } from '../processos.api';
import { Process } from '../../model/Process';

@Injectable()
export class ProcessService {

  constructor(private http: HttpClient) {}

  createOrUpdate(process: Process){
    if(process.id != null && process.id != ''){
      return this.http.put(`${PROCESSOS_API}/api/process`,process);
    } else {
      process.id = null;
      return this.http.post(`${PROCESSOS_API}/api/process`, process);
    }
  }

  findAll(page:number,count:number){
    return this.http.get(`${PROCESSOS_API}/api/process/${page}/${count}`);
  }

  findById(id:string){
    return this.http.get(`${PROCESSOS_API}/api/process/${id}`);
  }

  delete(id:string){
    return this.http.delete(`${PROCESSOS_API}/api/process/${id}`);
  }

  findByParams(page:number,count:number,t:Process){
    t.number = t.number == null ? 'uninformed' : t.number;
    return this.http.get(`${PROCESSOS_API}/api/process/${page}/${count}/${t.number}`);
  }


}