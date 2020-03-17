import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/user/user.service';

@Component({
  selector: 'process-home',
  templateUrl: './home.component.html'
})
export class HomeComponent {

  constructor(private userService: UserService) { }
  
  isAdmin() {
    return this.userService.isAdmin();
  }

  isScreening() {
    return this.userService.isScreening();
  }

  isCloser() {
    return this.userService.isCloser();
  }
}