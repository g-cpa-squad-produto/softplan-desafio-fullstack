import {EventEmitter, Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {User} from '../shared/model/user.model';
import {Login} from '../shared/model/login.model';
import {flatMap, map} from 'rxjs/operators';
import {SharedService} from './shared.service';

interface JwtToken {
  id_token: string;
}


type EntityResponseType = HttpResponse<User>;

@Injectable({
  providedIn: 'root'
})
export class UserService {

  public resourceUrl = 'api/usuarios';
  currentUserEmmiter = new EventEmitter<User>();

  constructor(
    private http: HttpClient,
    private sharedService: SharedService
  ) { }

  login(user: Login) {
    return this.http
      .post<JwtToken>('/api/authenticate', user)
      .pipe(map(response => this.authenticateSuccess(response)))
      .pipe(flatMap(() => this.fetch().pipe()));
  }


  private authenticateSuccess(response: JwtToken): void {
    this.sharedService.storeJwtToken(response.id_token);
  }

  fetch() {
    return this.http.get<User>('api/account');
  }

  create(usuario: User) {
    return this.http.post<User>(this.resourceUrl, usuario);
  }

  update(usuario: User) {
    return this.http.put<User>(this.resourceUrl, usuario);
  }

  find(id: number) {
    return this.http.get<User>(`${this.resourceUrl}/${id}`);
  }

  findAll() {
    return this.http.get<User[]>(this.resourceUrl);
  }

  findAllFinalizador() {
    return this.http.get<User[]>(this.resourceUrl + '/finalizador/');
  }

  delete(id: number) {
    return this.http.delete(`${this.resourceUrl}/${id}`);
  }
}
