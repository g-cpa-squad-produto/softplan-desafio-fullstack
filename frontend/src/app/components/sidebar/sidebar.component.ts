import { Component, OnInit } from '@angular/core';
import {SharedService} from '../../services/shared.service';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html'
})
export class SidebarComponent implements OnInit {

  public userName: string;

  constructor(private sharedService: SharedService) {
    this.userName = this.sharedService.getUserName();
  }

  ngOnInit() {
  }

}
