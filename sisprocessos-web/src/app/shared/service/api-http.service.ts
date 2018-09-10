import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../../../environments/environment';

export abstract class ApiHttpService {

  private readonly API_URL = environment.API_URL;

  protected constructor(protected http: HttpClient, public basePath: string) {
  }

  protected get<T>(path?: string | number): Observable<T> {
    return this.http.get<T>(this.getUrlServico(path));
  }

  protected post<T>(body: T, path?: string): Observable<T> {
    return this.http.post<T>(this.getUrlServico(path), body);
  }

  protected put<T>(body: T, path?: string): Observable<T> {
    return this.http.put<T>(this.getUrlServico(path), body);
  }

  protected delete<T>(path: string | number): Observable<T> {
    return this.http.delete<T>(this.getUrlServico(path));
  }

  protected getUrlServico(path?: string | number): string {
    return this.API_URL + this.basePath + (path !== undefined ? path : '');
  }

}
