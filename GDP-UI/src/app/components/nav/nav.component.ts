import { TokenService } from 'src/app/core/token/token.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit {

  constructor(private tokenService: TokenService, private route: Router) {

  }

  ngOnInit() {

  }

  public logout() {
    if (confirm('Deseja realmente sair ?')) {
        this.tokenService.removeToken();
        this.route.navigate(['login']);
    }
  }
}
