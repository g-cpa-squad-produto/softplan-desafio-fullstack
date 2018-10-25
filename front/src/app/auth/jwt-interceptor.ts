import { Injectable } from '@angular/core';
import 'rxjs/add/operator/do';
import { Router } from '@angular/router';
import 'rxjs/add/operator/do';
import { Observable } from 'rxjs';
import { HttpInterceptor, HttpHandler, HttpEvent, HttpRequest, HttpResponse, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Util } from '../util';
@Injectable()
//Classe para setar token em todas as requisicoes tirando o acesso para o login
export class TokenInterceptor implements HttpInterceptor {
    constructor(public router: Router) { }


    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        if ((request.url == Util._url + 'api-publica/login')) {
            return next.handle(request).do((event: HttpEvent<any>) => {
                if (event instanceof HttpResponse) {

                }
            }, (err: any) => {
                if (err instanceof HttpErrorResponse) {
                    if (err.error.exception == 'io.jsonwebtoken.ExpiredJwtException') {
                    }
                    if (err.status === 401) {
                    }
                }
            });
        } else {
            let headers: HttpHeaders = new HttpHeaders({
                "Authorization": window.localStorage.getItem('token')
            })

            return next.handle(request.clone({ headers: headers })).do((event: HttpEvent<any>) => {
                if (event instanceof HttpResponse) {
                }
            }, (err: any) => {
                if (err instanceof HttpErrorResponse) {
                    if (err.error.exception == 'io.jsonwebtoken.ExpiredJwtException') {
                    }
                    if (err.status === 401) {
                    }
                }
            });
        }
    }
}