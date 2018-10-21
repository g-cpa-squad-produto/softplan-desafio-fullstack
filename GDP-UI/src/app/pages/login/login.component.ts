import { User } from './../../model/user';
import { Component, OnInit } from '@angular/core';
import { LoginService } from './login.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(
    private formBuilder: FormBuilder,
    private loginService: LoginService) { }

  user: User = new User();
  loginForm: FormGroup;

  ngOnInit() {
    this.createForm();
  }

  public createForm() {
    this.loginForm = this.formBuilder.group({
      email: ['', Validators.required],
      password: ['', Validators.required]
  });
  }

  public isFieldValid (field): boolean {
       return (
         this.loginForm.get(field).touched
         && this.loginForm.get(field).status === 'INVALID');
  }

  public login() {

    console.log(this.loginForm.valid);

    if (this.loginForm.valid) {
      this.loginService.login();
    }
  }

}
