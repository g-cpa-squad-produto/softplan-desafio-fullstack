import {EventEmitter, Injectable} from '@angular/core';
import {User} from "../shared/model/user.model";

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

  public static getInstance(){
    if(this.instance == null){
      this.instance = new SharedService();
    }
    return this.instance;
  }

  isLoogegIn():boolean{
    if(this.user==null){
      return false;
    }
    return this.user.login != '';
  }
}
