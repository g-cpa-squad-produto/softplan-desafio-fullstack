import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from './user.model';
import envConf from '../../shared/env-config'

const URL = `${envConf.api.url}/api/v1/users`

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(
    private http: HttpClient
  ) { }

  getAllUsers(){
    console.log('getAllUsers URL=>', URL);
    return this.http.get<User[]>(`${URL}`);
  }

  getUserById(id){
    console.log('getAllUsers URL=>', URL);
    return this.http.get<User>(`${URL}/${id}`);
  }

  createUser(user) {
    console.log('user=>', user);
    console.log('createUser URL=>', URL);
    return this.http.post(`${URL}`, user);
  }

  updateUser(id, user) {
    console.log('user=>', user);
    console.log('updateUser URL=>', `${URL}/${id}`);
    return this.http.post(`${URL}/${id}`, user);
  }

  getUsersMock(){
    let usersMocked = [
      { id: '1', login: 'Luana', perfil: {id:'1', nome: 'perfil A'}},
      { id: '1', login: 'Luana', perfil: {id:'1', nome: 'perfil A'}},
      { id: '1', login: 'Luana', perfil: {id:'1', nome: 'perfil A'}}
    ]
    return Observable.create(observer => {
           observer.next(usersMocked)
           observer.complete()
    })
  }
}
