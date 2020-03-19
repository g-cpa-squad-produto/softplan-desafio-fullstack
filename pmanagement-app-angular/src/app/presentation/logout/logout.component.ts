import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/domain/service/AuthenticationService';
import { Router } from '@angular/router';

@Component({
  selector: "app-logout",
  template: "",
  styleUrls: []
})
export class LogoutComponent {
  constructor(
    private router: Router,
    private authenticationService: AuthenticationService
  ) {
    authenticationService.logout();
    this.router.navigate(["/"]);
  }
}
