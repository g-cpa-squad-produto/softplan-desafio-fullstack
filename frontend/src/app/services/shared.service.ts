import {EventEmitter, Injectable, OnInit} from '@angular/core';
import {User} from '../shared/model/user.model';
import {LocalStorageService} from "ngx-webstorage";

@Injectable({
  providedIn: 'root'
})
export class SharedService {

  showTemplate = new EventEmitter<boolean>();

  constructor(
    private $localStorage: LocalStorageService) {
    this.showTemplate.emit(this.isLoogegIn());
  }


  isLoogegIn(): boolean {
    return this.getJwtToken() !== undefined;
  }

  getJwtToken(): string {
    return this.$localStorage.retrieve('authenticationToken');
  }
}
