import { Process } from 'src/app/model/process';
import { HttpService } from './../http/http.service';
import { Injectable } from '@angular/core';
import { GenericicService } from './generic.service';

const endPoint = 'process';

@Injectable({ providedIn: 'root' })
export class ProcessService extends GenericicService<Process> {

  constructor(httpService: HttpService<Process>) {
    super(endPoint, httpService, );
  }

}
