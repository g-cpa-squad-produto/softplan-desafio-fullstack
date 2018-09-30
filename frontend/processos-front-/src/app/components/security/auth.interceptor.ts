import { Injectable } from '@angular/core';
import { SharedService } from './../../services/shared.service';
import { HttpInterceptor, HttpHandler, HttpEvent, HttpRequest } from "@angular/common/http";
import { Observable } from "rxjs";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

    shared : SharedService;
    constructor() { 
          this.shared = SharedService.getInstance();
    }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>>  {
        let authRequest : any;
        if(this.shared.isLoggedIn()){
            authRequest = req.clone({
                setHeaders: {
                    'Authorization' : this.shared.token
                }
            });
            return next.handle(authRequest);
        } else {
            return next.handle(req);
        }
    }

}