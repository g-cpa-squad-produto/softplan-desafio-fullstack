import { Component, OnInit } from '@angular/core';
import {Observable} from 'rxjs';
import {LocalStorageService} from 'ngx-webstorage';
import {SharedService} from '../../services/shared.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html'
})
export class HeaderComponent implements OnInit {

  constructor(
    private router: Router,
    private sharedService: SharedService
  ) { }

  ngOnInit() {
  }

  logout() {
    this.sharedService.removeJwtToken();
    this.sharedService.showTemplate.emit(false);
    this.router.navigate(['/']);
  }
}
