import { MassegesService } from './../../../core/messeges/messages.service';
import { Component, OnInit } from '@angular/core';
import { ProfileTypes } from 'src/app/model/profile-types';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { UserService } from 'src/app/core/service/user.service';
import { User } from 'src/app/model/user';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-form-user',
  templateUrl: './form-user.component.html',
  styleUrls: ['./form-user.component.css']
})
export class FormUserComponent implements OnInit {
  public profiles = ['ADMIN', 'FINALIZADOR', 'TRIADOR'];
  public formUser: FormGroup;

  public user: User = new User();
  public id: string;

  constructor(
    private formBuilder: FormBuilder,
    private activeRoute: ActivatedRoute,
    private massagesServer: MassegesService,
    private userService: UserService,
    private route: Router
  ) {}

  ngOnInit() {
    this.createForm();
    this.id = this.activeRoute.snapshot.paramMap.get('id');
    if (this.id) {
      this.userService.findById(this.id).subscribe(user => {
        this.user = new User(user);
        this.formUser.patchValue(user);
      });
    }
  }

  public createForm() {
    if (this.id) {
        this.formUser = this.formBuilder.group({
          id: [],
          name: ['', Validators.required],
          lastName: ['', Validators.required],
          profile: ['', Validators.required]
        });
    } else {
      this.formUser = this.formBuilder.group({
        id: [],
        password: ['', Validators.required],
        login: ['', Validators.required],
        name: ['', Validators.required],
        lastName: ['', Validators.required],
        profile: ['', Validators.required]
      });
    }
  }

  public update() {
    this.user = new User(this.formUser.value);
    this.userService.update(this.user).subscribe(() => {
      this.massagesServer.success('Usuário atualizado com sucesso!', 'Success');
      this.route.navigate(['usuarios']);
    });
 }

  public create() {
    this.user = new User(this.formUser.value);
    this.userService.salve(this.user).subscribe(() => {
      this.massagesServer.success('Usuário criado com sucesso!', 'Success');
      this.route.navigate(['usuarios']);
    });
  }

}
