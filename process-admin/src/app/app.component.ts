import { Component } from '@angular/core';
import { SharedService } from './services/shared.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {
  title = 'process-admin';
  isLoggedIn: boolean = false;
  public shared: SharedService;

  constructor(){
    this.shared = SharedService.getInstance();    
  }

  ngOnInit(){
    this.shared.showTemplate.subscribe(
      show => this.isLoggedIn = show
    );
  }

}