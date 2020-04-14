import { Component, OnInit } from '@angular/core';

import { UserService } from '../../../services/user.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss']
})
export class UsersComponent implements OnInit {
  displayedColumns: string[] = ['id', 'name', 'email', 'roles'];
  data: any;

  resultsLength = 0;
  isLoadingResults = true;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.isLoadingResults = true;
    this.userService.listUsers()
      .subscribe((users) => {
        this.data = users
      });
      this.isLoadingResults = false;
  }
}

