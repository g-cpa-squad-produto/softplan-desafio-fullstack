import { HttpService } from './../http/http.service';
import { Observable } from 'rxjs';


export class GenericicService<T> {

  constructor( private endPoint: string,  private httpService: HttpService<T>) {}

  public findAll(): Observable<T[]> {
    return this.httpService.get(`${this.endPoint}`);
  }

  public findById(id: number) {
      return  this.httpService.getOne(`${this.endPoint}\/${id}`);
  }
  public delete(id: number): Observable<any> {
    return this.httpService.delete(`${this.endPoint}`, id);
  }

  public update (t: T) {
      return this.httpService.put(t, `${this.endPoint}`);
  }

  public salve(t: T) {
    return this.httpService.post(t, `${this.endPoint}`);
  }

}
