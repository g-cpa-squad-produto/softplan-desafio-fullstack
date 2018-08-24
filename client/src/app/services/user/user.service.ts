import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor() { }

  getUsers(){
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
