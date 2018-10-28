import { ProcessService } from '../../../core/service/process.service';
import { Injectable } from '@angular/core';

import { Resolve, ActivatedRoute, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Process } from 'src/app/model/process';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class ShowScreeningResolver implements Resolve<Observable<Process>> {
  constructor(private processService: ProcessService) { }

  public resolve(route: ActivatedRouteSnapshot) {
    const id = (route.params['id']);
    if (id) {
     return  this.processService.findById(id);
    }
  }
}


