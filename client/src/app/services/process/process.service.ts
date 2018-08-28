import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Process } from './process.model';
import envConf from '../../shared/env-config'

const URL = `${envConf.api.url}/api/v1/processes`

@Injectable({
  providedIn: 'root'
})
export class ProcessService {

  constructor(
    private http: HttpClient
  ) { }

  getAllProcesses(){
    console.log('getAllProcesses URL=>', URL);
    return this.http.get<Process[]>(`${URL}`);
  }

  getProcessById(id){
    console.log('getProcessById URL=>', URL);
    return this.http.get<Process>(`${URL}/${id}`);
  }

  createProcess(process) {
    console.log('user=>', process);
    console.log('createProcess URL=>', URL);
    return this.http.post(`${URL}`, process);
  }

  updateProcess(id, process) {
    console.log('user=>', process);
    console.log('updateUser URL=>', `${URL}/${id}`);
    return this.http.post(`${URL}/${id}`, process);
  }
  
}
