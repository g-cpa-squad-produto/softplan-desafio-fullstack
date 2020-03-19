import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/domain/entity/user';
import { TemplateViewComponent } from 'src/app/presentation/authenticated/template-view/template-view.component';
import { Router } from '@angular/router';

@Component({
  selector: "app-home",
  templateUrl: "./home.component.html",
  styleUrls: ["./home.component.scss"]
})
export class HomeComponent extends TemplateViewComponent implements OnInit {
  currentUser: User = undefined;

  constructor(
    private router: Router,
  ) {
    super();
  }

  ngOnInit(): void {
    this.currentUser = JSON.parse(localStorage.getItem("currentUser"));
  }
}
