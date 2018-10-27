import { ActivatedRoute } from '@angular/router';
import { Feedback } from './../../../model/feedback';
import { FinalizeService } from './../../../core/service/finalize.service';
import { Injectable } from '@angular/core';

import { Resolve } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class ShowFinalizeResolver implements Resolve<Observable<Feedback[]>> {
  constructor(public finalizeService: FinalizeService,
              public activatedRoute: ActivatedRoute) {}
  public resolve() {
    const process =  this.activatedRoute.snapshot.data['process'];
    if (process) {
      return this.finalizeService.findAllByProcess(process);
    }
  }
}
