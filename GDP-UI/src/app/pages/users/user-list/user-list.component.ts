import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { UserDTO } from 'src/app/model/user.dto';
import { User } from 'src/app/model/user';
import { UserService } from 'src/app/core/service/user.service';
import { TokenService } from 'src/app/core/token/token.service';
import { MassegesService } from 'src/app/core/messeges/messages.service';
import { tap } from 'rxjs/operators';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  private currentUser: UserDTO;
  public users: Array<User> = new Array<User>();

  constructor(private userService: UserService,
         private tokenService: TokenService,
         private route: Router,
         private massagesService: MassegesService
    ) { }

  ngOnInit() {
    this.getAllUsers();
    this.currentUser = this.tokenService.getTokenUserDTO();
  }

  public getAllUsers() {
     this.userService.findAll().subscribe(users => {
        this.users = [...users.filter((item, i) => {
          return item.login !== this.currentUser.login;
        })];
      });
  }

  public delete(id: number, index) {
    if (confirm('Deseja deletar este usuário ?')) {
        this.userService.delete(id).subscribe(() => {
            this.massagesService.success('Success', 'Delete Success');
              this.users.splice(index, 1);
              this.users = [...this.users];
          });
        }
    }
}
