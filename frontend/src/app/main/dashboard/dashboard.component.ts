import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { ProcessService } from 'src/app/services/process.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  user;
  process;

  constructor(
    private userService: UserService,
    private processService: ProcessService
  ) { }

  ngOnInit(): void {
    this.userService.listUsers().subscribe((res) => { this.user = res })
    this.processService.listProcesses().subscribe((res) => { this.process = res });

  }
}
