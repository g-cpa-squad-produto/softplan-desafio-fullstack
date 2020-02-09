import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {SharedService} from '../../services/shared.service';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {LocalStorageService} from "ngx-webstorage";

@Injectable({ providedIn: 'root' })
export class AuthInterceptor implements HttpInterceptor {


  constructor( private $localStorage: LocalStorageService) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let authRequest: any;
    const jwt = this.$localStorage.retrieve('authenticationToken');
    if (jwt !== undefined) {
      authRequest = req.clone({
        setHeaders: {
          Authorization : `Bearer ${jwt}`
        }
      });
      return next.handle(authRequest);
    }
    return next.handle(req);
  }

}
