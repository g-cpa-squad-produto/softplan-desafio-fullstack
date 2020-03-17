import { Injectable } from '@angular/core';

@Injectable({ 
  providedIn: 'root' 
})
export class ToastService {

  toasts: any[] = [];

  private show(text: string, classname: string) {
    this.toasts.push({text, classname});
  }

  showError(text) {
    this.show(text, 'bg-danger text-light');
  }

  showSuccess(text) {
    this.show(text, 'bg-success text-light');
  }

  remove(toast) {
    this.toasts = this.toasts.filter(t => t !== toast);
  }
}