import { Component, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'process-users-init',
  templateUrl: './users-init.component.html'
})
export class UsersInitComponent implements OnInit {
  
  users: any[] = [];

  constructor(public activeModal: NgbActiveModal) { }
  
  ngOnInit() {
    this.users = [
      {"idUser": 1, "name": "Administrador", "login": "admin", "password": "admin"},
      {"idUser": 2, "name": "Triador", "login": "triador", "password": "triador"},
      {"idUser": 3, "name": "Finalizador", "login": "finalizador", "password": "finalizador"}
    ];
  }
}