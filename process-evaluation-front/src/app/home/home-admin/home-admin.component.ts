import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { switchMap } from 'rxjs/operators';
import { faEdit, faTrashAlt } from '@fortawesome/free-solid-svg-icons';
import { UserService } from 'src/app/user/user.service';
import { User } from 'src/app/user/user';
import { Page } from 'src/app/shared/page';

@Component({
  selector: 'process-home-admin',
  templateUrl: './home-admin.component.html'
})
export class HomeAdminComponent implements OnInit {

  users: Page<User> = Object.assign({});
  page: number = 1;
  previousPage: number;
  maxRecords: number = 5;

  faIconEdit = faEdit;
  faIconRemove = faTrashAlt;
  
  constructor(private userService: UserService, private router: Router) { }

  ngOnInit() {
    this.findUsers();  
  }

  private findUsers() {
    this.userService.findAll(this.page - 1, this.maxRecords).subscribe(
      res => this.users = res
    );
  }

  loadPage(page: number) {    
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.findUsers();  
    }
  }

  newUser() {
    this.router.navigate(['/user']);
  }

  edit(idUser: number) {
    this.router.navigate(['/user', idUser]);
  }

  remove(idUser: number) {
    this.userService.delete(idUser).pipe(switchMap(() => this.userService.findAll(0, this.maxRecords)))
      .subscribe(res => this.users = res);
  }
}
