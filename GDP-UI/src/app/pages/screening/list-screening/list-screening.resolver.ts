import { ProcessService } from '../../../core/service/process.service';
import { Injectable } from '@angular/core';

import { Resolve } from '@angular/router';
import { Observable } from 'rxjs';
import { Process } from 'src/app/model/process';

@Injectable({ providedIn: 'root' })
export class ListScreeningResolver implements Resolve<Observable<Process[]>> {
  constructor(public processService: ProcessService) {}
  public resolve() {
    return this.processService.findAll();
  }
}
