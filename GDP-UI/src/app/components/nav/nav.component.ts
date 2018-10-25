import { UserDTO } from 'src/app/model/user.dto';
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

  public user: UserDTO ;

  ngOnInit() {
    this.user = this.tokenService.getTokenUserDTO();
  }

  public logout() {
    if (confirm('Deseja realmente sair ?')) {
        this.tokenService.removeToken();
        this.route.navigate(['login']);
    }
  }
}
