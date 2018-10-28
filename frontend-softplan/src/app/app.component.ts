import { Component } from '@angular/core';
import { SharedService } from './login/shared.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  showTemplate: Boolean = false;
  public shared: SharedService;
  title = 'Sistema de Processos';

  constructor() {
    this.shared = SharedService.getInstance();
    this.shared.showTemplate.subscribe(show => this.showTemplate = show);
  }
}
