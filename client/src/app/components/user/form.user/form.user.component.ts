import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { UserService } from '../../../services/user/user.service';
import { User } from '../../../services/user/user.model'

@Component({
  selector: 'app-form-user',
  templateUrl: './form.user.component.html',
  styleUrls: ['./form.user.component.scss']
})
export class FormUserComponent implements OnInit {

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private userService: UserService
  ) { }

  userId: number;
  user: User = new User();

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      if(params.get('id')){
        this.userId = Number(params.get('id'))
        this.userService.getUserById(this.userId).subscribe(
          u =>{
              this.user = u;
          }
        )
      }else{
        this.user = new User();
      }
    });
  }

  save(){
    if(this.user.id){
      this.upDateUser();
    }else{
      this.createUser();
    }
  }

  createUser(){
    this.userService.createUser(this.user).subscribe(
      u =>{
        this.router.navigate(['/user'])
      },
      err =>{
        console.error(err);
      });
  }

  upDateUser(){
    this.userService.updateUser(this.userId, this.user).subscribe(
      u =>{
        this.router.navigate(['/user'])
      },
      err =>{
        console.error(err);
      });
  }

  voltar() {
    window.history.back();
  }

}
