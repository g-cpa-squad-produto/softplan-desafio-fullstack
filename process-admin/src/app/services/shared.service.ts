import { Injectable, EventEmitter } from '@angular/core';
import { User } from '../model/user.model';

@Injectable({
  providedIn: 'root'
})

export class SharedService {

  public static instance: SharedService = null;
  user: User;
  token: string;
  showTemplate = new EventEmitter<boolean>();

  constructor() {
    return SharedService.instance = SharedService.instance || this;
  }

  public static getInstance() {    
    if (this.instance == null) {
      this.instance = new SharedService();
    } 
    return this.instance;
  }
  
  isLoggedIn(): boolean {
    if (this.user == null) {
      console.log('Usuário não logado');
      return false;
    } 
    console.log('Usuário logado');
    return this.user.email != '';
  }

}
