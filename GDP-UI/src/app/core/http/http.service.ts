import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { tap } from 'rxjs/operators';
import { MessegesService } from '../messeges/messages.service';
import { API_URL } from '../const';
import { Token } from 'src/app/model/token';

@Injectable({ providedIn: 'root'})
export class HttpService<T> {
    constructor (
        private http: HttpClient,
        private messageService: MessegesService ) {

    }

    public handlerError (error: HttpErrorResponse) {
            if (error.status === 404) {
                this.messageService.error('Not Found');
            } else if (error.status === 401) {
                this.messageService.error('Authentication field');
            } else if (error.status === 403) {
                this.messageService.error('Forbidden');
            } else if (error.status === 501) {
                this.messageService.error('Internal Error');
            } else {
                this.messageService.error(error.message);
            }

    }

    public post(item: T, endpoint): Observable<T> {
        return this.http
          .post<T>(`${API_URL}/${endpoint}`, item)
          .pipe(tap(data => {
              return data;
            }, (error: HttpErrorResponse) => {
                 this.handlerError(error);
            }));
      }

      public login(item: T, endpoint): Observable<Token> {
        return this.http
          .post<Token>(`${API_URL}/${endpoint}`, item)
          .pipe(tap(data => {
              return data;
            }, (error: HttpErrorResponse) => {
              this.handlerError(error);
              console.log(error);
            }));
      }
}
