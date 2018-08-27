import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';

@Component({
  selector: 'app-custom-tool-bar',
  templateUrl: './custom.toolbar.component.html',
  styleUrls: ['./custom.toolbar.component.css']
})
export class CustomToolBarComponent {

  constructor(
    private router: Router
  ){}

  routeFormUser() {
    this.router.navigate([`/user`]);
  }

}
