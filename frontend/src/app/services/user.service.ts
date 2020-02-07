import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "../shared/model/user.model";
import {Login} from "../shared/model/login.model";
import {flatMap, map, tap} from "rxjs/operators";
import {LocalStorageService} from "ngx-webstorage";
import {Observable} from "rxjs";

type JwtToken = {
  id_token: string;
};

@Injectable({
  providedIn: 'root'
})
export class UserService {

  public resourceUrl = 'api/usuarios';

  constructor(
    private http: HttpClient,
    private $localStorage: LocalStorageService
  ) { }

  login(user:Login){
    return this.http
      .post<JwtToken>('/api/authenticate', user)
      .pipe(map(response => this.authenticateSuccess(response)))
      .pipe(flatMap(() => this.fetch().pipe()));
  }


  private authenticateSuccess(response: JwtToken): void {
    const jwt = response.id_token;
    this.$localStorage.store('authenticationToken', jwt);
  }

  fetch(){
    return this.http.get<User>('api/account');
  }

  create(usuario: User){
    return this.http.post<User>(this.resourceUrl, usuario);
  }

  update(usuario: User){
    return this.http.put<User>(this.resourceUrl, usuario);
  }

  find(id: number){
    return this.http.get<User>(`${this.resourceUrl}/${id}`);
  }

  findAll(){
    return this.http.get<User>(this.resourceUrl);
  }

  delete(id: number){
    return this.http.delete(`${this.resourceUrl}/${id}`);
  }
}
