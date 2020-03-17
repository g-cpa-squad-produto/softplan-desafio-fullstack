import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../auth/auth.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { UsersInitComponent } from './users-init/users-init.component';
import { ToastService } from '../../shared/toast/toast.service';

@Component({
  selector: 'process-login',
  templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit {

  formLogin: FormGroup;

  constructor(private formBuilder: FormBuilder, 
              private router: Router, 
              private authService: AuthService,
              private modalService: NgbModal,
              private toastService: ToastService) { }

  ngOnInit() {
    this.formLogin = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  login() {
    const username = this.formLogin.get('username').value;
    const password = this.formLogin.get('password').value;

    this.authService.authenticate(username, password).subscribe(
      () =>  this.router.navigate(['/home']),
      (error) => this.toastService.showError(error.error[0].mensagemUsuario)
    );
  }

  showUsers() {
    this.modalService.open(UsersInitComponent);
  }
}