import { Component, OnInit } from '@angular/core';

import { ProcessService } from 'src/app/services/process.service';
import { User } from 'src/app/models/user.model';
import { UserService } from 'src/app/services/user.service';
import { AuthenticationService } from 'src/app/services/authentication.service';


@Component({
  selector: 'app-processes',
  templateUrl: './processes.component.html',
  styleUrls: ['./processes.component.scss']
})
export class ProcessesComponent implements OnInit {
  displayedColumns: string[] = ['id', 'number', 'users', 'report'];
  data: any;
  users: any[];
  user: User;

  resultsLength = 0;
  isLoadingResults = true;

  constructor(
    private processService: ProcessService,
    private userService: UserService,
    private authenticationService: AuthenticationService,
  ) { }

  ngOnInit(): void {
    this.isLoadingResults = true;
    this.userService.listUsers()
      .subscribe((userslist) => {
        this.users = userslist
      });

    this.user = this.authenticationService.getUser();

    this.processService.listProcesses()
      .subscribe((processes) => {
        this.data = processes;
      });

    this.isLoadingResults = false;
  }
}

