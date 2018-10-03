import { PROCESSOS_API } from './../processos.api';
import { ProcessFeedback } from './../../model/ProcessFeedback';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ProcessFeedbackService {

  constructor(private http: HttpClient) {}

  createOrUpdate(process: ProcessFeedback){
    if(process.id != null && process.id != ''){
      return this.http.put(`${PROCESSOS_API}/api/process-feedback`,process);
    } else {
      process.id = null;
      return this.http.post(`${PROCESSOS_API}/api/process-feedback`, process);
    }
  }

  findAll(page:number,count:number){
    return this.http.get(`${PROCESSOS_API}/api/process-feedback/${page}/${count}`);
  }

  findById(id:string){
    return this.http.get(`${PROCESSOS_API}/api/process-feedback/${id}`);
  }

  delete(id:string){
    return this.http.delete(`${PROCESSOS_API}/api/process-feedback/${id}`);
  }

  findByProcess(page:number,count:number, id:string){
    return this.http.get(`${PROCESSOS_API}/api/process-feedback/${page}/${count}/${id}`);
  }

}
