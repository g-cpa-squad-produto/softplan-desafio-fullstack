import { Component, OnInit } from '@angular/core';
import { UserService } from '../../../services/user/user.service';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';

@Component({
  selector: 'app-search-user-component',
  templateUrl: './search.user.component.html',
  styleUrls: ['./search.user.component.scss']
})
export class SearchUserComponent implements OnInit {

  private users = [];

  // exibicao de mensagem de erro na tela
  isError: Boolean = false;
  error: string;

  // exibicao de loader na tela
  exibeProgress: Boolean = false;
  value: Number = 0;

  constructor(
    private userService: UserService,
    private router: Router
  ) { }

  routeFormUser() {
    this.router.navigate(['/user/form']);
  }

  back() {
    window.history.back();
  }

  ngOnInit() {
    const isMobile = /Android|iPhone/i.test(window.navigator.userAgent);
    console.log(`Plataforma ${window.navigator.userAgent}`);
    this.users =
    this.userService.getUsers()
      .subscribe(res => {
        console.log('>>>> get users res=', res);
        this.exibeProgress = false;
        // this.router.navigate(['/users'])
    },
    error => {
      console.log('error service get users ==>', error);
      this.exibeProgress = false;
      this.isError = true;
      this.error = `Have no users`;
    });

  }
}
