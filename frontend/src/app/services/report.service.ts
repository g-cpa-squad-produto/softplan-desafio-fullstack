import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Report} from "../shared/model/report.model";

@Injectable({
  providedIn: 'root'
})
export class ReportService {
  public resourceUrl = 'api/reports';

  constructor(protected http: HttpClient) {}

  create(report: Report){
    return this.http.post<Report>(this.resourceUrl, report);
  }

  update(report: Report){
    return this.http.put<Report>(this.resourceUrl, report);
  }

  find(id: number){
    return this.http.get<Report>(`${this.resourceUrl}/${id}`);
  }

  findAll(){
    return this.http.get<Report>(this.resourceUrl);
  }

  delete(id: number){
    return this.http.delete(`${this.resourceUrl}/${id}`);
  }
}
