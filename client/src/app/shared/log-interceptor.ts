import { Injectable } from '@angular/core'

import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http'

import { tap } from 'rxjs/operators'

@Injectable()
export class LoggingInterceptor implements HttpInterceptor {
  intercept(request: HttpRequest<any>, next: HttpHandler) {
    return next.handle(request).pipe(
       tap(
           response => console.log("[DEBUG] HttpClient request=", request, 'response=', response), 
           error => console.log("[ERROR] HttpClient request=", request, 'error=', error)
       ));
  }
}