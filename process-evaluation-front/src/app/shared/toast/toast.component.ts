import { Component } from '@angular/core';
import { ToastService } from './toast.service';

@Component({
  selector: 'process-toasts',
  template: `
    <ngb-toast
      *ngFor="let toast of toastService.toasts"
      [class]="toast.classname"
      [autohide]="true"
      [delay]="3500"
      (hide)="toastService.remove(toast)">
      {{ toast.text }}
    </ngb-toast>
  `,
  host: {'[class.ngb-toasts]': 'true'}
})
export class ToastComponent {

  constructor(public toastService: ToastService) { }
  
}