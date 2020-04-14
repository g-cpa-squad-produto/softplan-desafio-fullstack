import { Component, OnInit } from '@angular/core';

import { AuthenticationService } from './services/authentication.service';
import { User } from './models/user.model';
import { SharedService } from './services/shared.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'Gestão de Processos';
  user: User;
  isMenuOpen = true;
  contentMargin = 240;

  constructor(
    private authService: AuthenticationService,
    private sharedService: SharedService
  ) {

    this.sharedService.getLoginEvent().subscribe(() => {
      this.ngOnInit();
    });
  }

  ngOnInit(): void {
    this.user = this.authService.getUser();
  }

  onToolbarMenuToggle() {
    console.log('On toolbar toggled', this.isMenuOpen);
    this.isMenuOpen = !this.isMenuOpen;

    if (!this.isMenuOpen) {
      this.contentMargin = 70;
    } else {
      this.contentMargin = 240;
    }
  }

  logout(): void {
    this.authService.logout();
    this.ngOnInit();
  }
}
