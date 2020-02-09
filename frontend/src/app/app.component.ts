import {Component, OnInit} from '@angular/core';
import {SharedService} from './services/shared.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent implements OnInit {

  showTemplate = false;
  constructor( private shared: SharedService) {
    this.showTemplate = shared.isLoogegIn();
  }

  ngOnInit(): void {
    this.shared.showTemplate.subscribe(
      show => this.showTemplate = show
    );
  }

  showContentWarper() {
    return {
      'content-warpper' : this.shared.isLoogegIn()
    };
  }
}
