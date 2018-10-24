import { Component, OnInit } from '@angular/core';
import { UserDTO } from 'src/app/model/user.dto';
import { User } from 'src/app/model/user';
import { UserService } from 'src/app/core/service/user.service';
import { TokenService } from 'src/app/core/token/token.service';
import { MassegesService } from 'src/app/core/messeges/messages.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  currentUser: UserDTO;
  users: User[];

  constructor(private userService: UserService,
         private tokenService: TokenService,
         private massagesService: MassegesService,
         private activatedRoute: ActivatedRoute
    ) { }

  ngOnInit() {
    this.getAllUsers();
    this.currentUser = this.tokenService.getTokenUserDTO();
  }

  public getAllUsers() {
    this.userService.findAll().subscribe(users => {
      this.users = users.filter((item, i) => {
        return item.login !== this.currentUser.login;
      });
    });
  }

  public delete(id: number) {
    if (confirm('Delete this user ?')) {
        this.userService.delete(id).subscribe(data => {
          this.getAllUsers();
          this.massagesService.success('Success', 'Delete Success');
        });
    }
  }

  public show(id: number) {
      alert(id);
  }
}
