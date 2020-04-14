import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class SharedService {
  private subject = new Subject<any>();
  
  sendLoginEvent() {
    this.subject.next();
  }
  getLoginEvent(): Observable<any> {
    return this.subject.asObservable();
  }
}