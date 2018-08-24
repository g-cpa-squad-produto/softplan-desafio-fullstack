import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-form-user',
  templateUrl: './form.user.component.html',
  styleUrls: ['./form.user.component.scss']
})
export class FormUserComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  voltar() {
    window.history.back();
  }

}
