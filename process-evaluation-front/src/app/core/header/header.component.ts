import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { UserService } from '../../user/user.service';


@Component({
  selector: 'process-header',
  templateUrl: './header.component.html'
})
export class HeaderComponent implements OnInit {

  user$: Observable<String>;

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit() {
    this.user$ = this.userService.getUser();
  }

  logout() {
    this.userService.logout();    
    this.router.navigate(['/login']);
  }

}