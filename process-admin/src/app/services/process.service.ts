import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Process } from './../model/process.model';
import { PROCESS_ADMIN_API } from './process-admin.api';

@Injectable({
  providedIn: 'root'
})
export class ProcessService {

  constructor(private http: HttpClient) { }

  createOrUpdate(process: Process) {
    if (process.id != null && process.id != '') {
      return this.http.put(`${PROCESS_ADMIN_API}/api/process`, process);
    } else {
      process.id = null;
      process.status = 'New'
      return this.http.post(`${PROCESS_ADMIN_API}/api/process`, process);
    }
  }

  findAll(page: number, count: number) {
    return this.http.get(`${PROCESS_ADMIN_API}/api/process/${page}/${count}`);
  }

  findById(id: string) {
    return this.http.get(`${PROCESS_ADMIN_API}/api/process/${id}`);
  }

  delete(id: string) {
    return this.http.delete(`${PROCESS_ADMIN_API}/api/process/${id}`);
  }

  findByParams(page: number, count: number, assignedToMe: boolean, p: Process) {
    p.number = p.number == null ? 0 : p.number;
    p.subject = p.subject == '' ? 'uninformed' : p.subject;
    p.status = p.status == '' ? 'uninformed' : p.status;
    p.priority = p.priority == '' ? 'uninformed' : p.priority;
    return this.http.get(`${PROCESS_ADMIN_API}/api/process/${page}/${count}/${p.subject}/${p.status}/${p.priority}/${assignedToMe}`);
  }

  changeStatus(status: string, process: Process) {
    return this.http.put(`${PROCESS_ADMIN_API}/api/process/${process.id}/${process.status}`, process);
  }

  summary() {
    return this.http.get(`${PROCESS_ADMIN_API}/api/process/summary`);
  }
}
