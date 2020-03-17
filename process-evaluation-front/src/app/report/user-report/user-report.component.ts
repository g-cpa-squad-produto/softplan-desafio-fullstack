import { Component, OnInit, Input } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { UserService } from 'src/app/user/user.service';
import { User } from 'src/app/user/user';
import { Report } from '../report';
import { faThumbsUp } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'process-user-report',
  templateUrl: './user-report.component.html'
})
export class UserReportComponent implements OnInit {

  users: User[] = [];
  @Input() addeds: Report[] = [];
  faIcon = faThumbsUp;

  constructor(public activeModal: NgbActiveModal, 
              private userService: UserService) { }

  ngOnInit() {
    this.userService.findClosers().subscribe(
      res => {
        this.users = res;
        this.users = this.users.filter(u => !this.isAdded(u));
      }
    )
  }

  isAdded(user) {
    if (!this.addeds.length) {
      return false;
    }

    return this.addeds.filter(r => r.author === user.idUser).length > 0;
  }

  select(user: User) {
    this.activeModal.close(user);
  }
}