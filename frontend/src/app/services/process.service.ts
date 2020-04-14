import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { Process } from '../models/process.model';
import { environment } from '../../environments/environment';

@Injectable({
    providedIn: 'root'
})
export class ProcessService {
    constructor(private http: HttpClient) {}

    listProcesses(): Observable<Process> {
        return this.http.get<Process>(`${environment.api.url}/processes`);
    }

    getProcess(id: number): Observable<Process> {
        return this.http.get<Process>(`${environment.api.url}/processes/${id}`);
    }

    createProcess(process: Process): Observable<any> {
        console.log(process);
        const href = `${environment.api.url}/processes/`;
        return this.http.post<any>(`${href}`, {...process});
    }

    updateProcess(id: number, process: Process): Observable<any> {

        const href = `${environment.api.url}/processes/${id}`;
        return this.http.put<any>(`${href}`, {...process});
    }

    deleteProcess(id: number): Observable<Process> {
        return this.http.delete<Process>(`${environment.api.url}/processes/${id}`);
    }
}
