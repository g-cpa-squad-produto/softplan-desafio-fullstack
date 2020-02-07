import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Process} from "../shared/model/process.model";

@Injectable({
  providedIn: 'root'
})
export class ProcessService {
  public resourceUrl = 'api/processes';

  constructor(protected http: HttpClient) {}

  create(process: Process){
    return this.http.post<Process>(this.resourceUrl, process);
  }

  update(process: Process){
    return this.http.put<Process>(this.resourceUrl, process);
  }

  find(id: number) {
    return this.http.get<Process>(`${this.resourceUrl}/${id}`);
  }

  findAll() {
    return this.http.get<Process>(this.resourceUrl);
  }

  delete(id: number){
    return this.http.delete(`${this.resourceUrl}/${id}`);
  }
}
