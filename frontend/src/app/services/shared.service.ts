import {EventEmitter, Injectable, OnInit} from '@angular/core';
import {LocalStorageService} from 'ngx-webstorage';

@Injectable({
  providedIn: 'root'
})
export class SharedService {

  private readonly authenticationToken = 'authenticationToken';
  private readonly userName = 'userName';

  showTemplate = new EventEmitter<boolean>();

  constructor(
    private $localStorage: LocalStorageService) {
    this.showTemplate.emit(this.isLoogegIn());
  }

  isLoogegIn(): boolean {
    return this.getJwtToken() !== null;
  }


  getJwtToken(): string {
    return this.$localStorage.retrieve(this.authenticationToken);
  }

  storeJwtToken(jwt: string) {
    this.$localStorage.store(this.authenticationToken, jwt);
  }

  removeJwtToken() {
    this.$localStorage.clear(this.authenticationToken);
  }

  storeUserName(userName: string){
    this.$localStorage.store(this.userName, userName);
  }

  getUserName() {
    return this.$localStorage.retrieve(this.userName);
  }

  removeUserName() {
    this.$localStorage.clear(this.userName);
  }
}
