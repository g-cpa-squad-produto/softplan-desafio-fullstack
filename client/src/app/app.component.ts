
import { Subscription } from 'rxjs'

import { Component } from '@angular/core'
import {trigger, animate, style, group, animateChild, query, stagger, transition} from '@angular/animations'

export const routerTransition =
trigger('routerTransition', [
      transition('* <=> *', [
        // Initial state of new route
        query(':enter',
          style({
            position: 'fixed',
            width:'100%',
            transform: 'translateX(100%)',
            opacity: 1
          }),
          {optional:true}),
        // move page off screen right on leave
        query(':leave',
          animate('300ms ease-out',
            style({
              position: 'fixed',
              width:'100%',
              transform: 'translateX(-100%)',
              opacity: 1
            })
          ),
        {optional:true}),

        // move page in screen from left to right
        query(':enter',
          animate('300ms ease-in',
            style({
              opacity: 1,
              transform: 'translateX(0%)'
            })
          ),
        {optional:true}),
        // --
    ])
])

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  animations: [ routerTransition ],
})
export class AppComponent {
  title = 'Titulo'
  loading = false

  private loaderSubscription: Subscription

  constructor(
  ) { }

  ngOnInit() {

  }

  voltar() {
    window.history.back();
  }

  getState(outlet) {
    return outlet.activatedRouteData.state;
  }
  ngOnDestroy() {
    this.loaderSubscription.unsubscribe()
  }

}
