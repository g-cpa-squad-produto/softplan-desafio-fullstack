import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable, throwError} from 'rxjs';
import {LoginComponent} from './login/login.component';
import {catchError} from 'rxjs/operators';
import {SharedService} from '../../services/shared.service';

@Injectable({providedIn: 'root'})
export class AuthInterceptor implements HttpInterceptor {


  constructor(
    private sharedService: SharedService) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const jwt = this.sharedService.getJwtToken();

    if (jwt !== undefined) {
      req = req.clone({
        setHeaders: {
          Authorization: `Bearer ${jwt}`
        }
      });

      return next.handle(req).pipe(catchError(err => {
        if (err.status === 401) {
          // auto logout if 401 response returned from api
          this.sharedService.removeJwtToken();
          location.reload();
        }

        const error = err.error.message || err.statusText;
        return throwError(error);
      }));
    }
  }
}
