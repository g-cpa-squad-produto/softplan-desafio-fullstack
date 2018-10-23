import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { User } from 'src/app/model/user';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  user: User;
  constructor(
    private activatedRoute: ActivatedRoute
    ) {
     }

  ngOnInit() {
    this.user = this.activatedRoute.snapshot.data['user'];
  }

}
