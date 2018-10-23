import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-container',
  templateUrl: './container.component.html',
  styleUrls: ['./container.component.css']
})
export class ContainerComponent implements OnInit {
  currentUser;
  constructor(         private activatedRoute: ActivatedRoute
    ) { }

  ngOnInit() {
    this.currentUser = this.activatedRoute.snapshot.data['user'];


    console.log(this.currentUser);
  }

}
