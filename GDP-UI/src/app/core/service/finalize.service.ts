import { Observable } from 'rxjs';
import { Process } from 'src/app/model/process';
import { Feedback } from './../../model/feedback';
import { HttpService } from './../http/http.service';
import { Injectable } from '@angular/core';
import { GenericicService } from './generic.service';

const endPoint = 'feedback';

@Injectable({ providedIn: 'root' })
export class FinalizeService extends GenericicService<Feedback> {

  constructor(httpService: HttpService<Feedback>) {
    super(endPoint, httpService);
  }

  public findAllByProcess(process: Process): Observable<Array<Feedback>>  {
      return this.post(process, 'by-process');
  }

}
