import { MassegesService } from './../../core/messeges/messages.service';
import { UserService } from './../../core/service/user.service';
import { Component, OnInit, Input } from '@angular/core';
import { User } from 'src/app/model/user';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-list-table',
  templateUrl: './list-table.component.html',
  styleUrls: ['./list-table.component.css']
})
export class ListTableComponent implements OnInit {


  currentUser: User;
  users: User[];

  constructor(private userService: UserService,
         private massagesService: MassegesService,
         private activatedRoute: ActivatedRoute
    ) { }

  ngOnInit() {
    this.getAllUsers();
    this.currentUser = this.activatedRoute.snapshot.data['user'];
  }

  public getAllUsers() {
    this.userService.all().subscribe(users => {
      this.users = users.filter((item, i) => {
        return item.id !== this.currentUser.id;
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
}
