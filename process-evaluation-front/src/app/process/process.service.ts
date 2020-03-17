import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpParams, HttpClient } from '@angular/common/http';
import { Process } from './process';
import { Page } from '../shared/page';

@Injectable({
  providedIn: 'root'
})
export class ProcessService {

  constructor(private http: HttpClient) { }

  findAll(page: number, size: number) {
    const url = `${environment.api}/process`;
    let params = new HttpParams();
    params = params.append('page', page.toString());
    params = params.append('size', size.toString());
    return this.http.get<Page<Process>>(url);
  }

  findById(idProcess: number) {
    const url = `${environment.api}/process/${idProcess}`;
    return this.http.get<Process>(url);
  }

  insert(process: Process) {
    const url = `${environment.api}/process`;
    return this.http.post<Process>(url, process);
  }

  findMyPendings() {
    const url = `${environment.api}/process/myPendings`;
    return this.http.get<Process[]>(url);
  }
}