import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ReportService {

  constructor(private http: HttpClient) { }  

  addTextToReport(idReport: number, text: string) {
    const url = `${environment.api}/reports/${idReport}`;
    return this.http.put(url, text);
  }
}