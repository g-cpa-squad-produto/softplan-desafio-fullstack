import { UserService } from './services/user/user.service';
import { Component } from '@angular/core';
import { SharedService } from './services/shared.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  
  showTemplate: boolean = false;
  public shared: SharedService;
  
  constructor(private userService: UserService){
    this.shared = SharedService.getInstance();
  }

  ngOnInit(){
    this.shared.showTemplate.subscribe(
      show => this.showTemplate = show
    );
  }

  showContentWrapper(){
    return {
      'content-wrapper': this.shared.isLoggedIn()
    }
  }

}