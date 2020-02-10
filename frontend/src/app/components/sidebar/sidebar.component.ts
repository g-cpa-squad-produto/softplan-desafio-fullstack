import {Component, OnInit} from '@angular/core';
import {UserService} from '../../services/user.service';
import {User} from '../../shared/model/user.model';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html'
})
export class SidebarComponent implements OnInit {

  public user: User;

  constructor(private userService: UserService) {
  }

  ngOnInit() {
    this.userService.currentUserEmmiter.subscribe(
        (user: User) => this.user = user
    );
  }

}
