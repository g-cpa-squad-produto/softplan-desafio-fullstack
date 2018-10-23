import { Component } from '@angular/core';

@Component({
  selector: 'my-app',
  template: `
    <app-header></app-header>
    <router-outlet></router-outlet>
  `,
  styles: []
})
export class AppComponent {}