import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthGuard } from 'src/app/services/auth/auth.guard';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  public isLoggedIn$: Observable<boolean>;

  constructor(private authService: AuthGuard) { }

  ngOnInit() {
    this.isLoggedIn$ = this.authService.isLoggedIn;
  }

  onLogout() {
    this.authService.logout();
  }

}
