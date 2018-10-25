import { UserService } from 'src/app/core/service/user.service';
import { User } from 'src/app/model/user';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-show-user',
  templateUrl: './show-user.component.html',
  styleUrls: ['./show-user.component.css']
})
export class ShowUserComponent implements OnInit {

  public user = new User();
  public id: string;
  constructor(private route: ActivatedRoute, private userService: UserService) { }

  ngOnInit() {
    this.id = this.route.snapshot.paramMap.get('id');
    this.getUser(this.id);
  }

  private getUser(id) {
      this.userService.findById(id).subscribe(user => {
          this.user = user;
      });
  }

}
