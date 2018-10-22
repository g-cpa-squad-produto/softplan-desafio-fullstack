import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private router: Router) { }
  netImage: any = "assets/images/a.png";

  LogOn() {
    localStorage['token'] = 'xptoh26410x5=50';
    this.router.navigate(['home']);
  }

  ngOnInit() {
  }

}

