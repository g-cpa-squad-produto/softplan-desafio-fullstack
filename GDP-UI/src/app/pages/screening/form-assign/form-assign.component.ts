import { UserService } from './../../../core/service/user.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/user';
import { Process } from 'src/app/model/process';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MassegesService } from 'src/app/core/messeges/messages.service';
import { ProcessService } from 'src/app/core/service/process.service';

@Component({
  selector: 'app-form-assign',
  templateUrl: './form-assign.component.html',
  styleUrls: ['./form-assign.component.css']
})
export class FormAssignComponent implements OnInit {

  users: User[];
  process: Process;
  formAssign: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private massagesServer: MassegesService,
    private processService: ProcessService,
    private userService: UserService,
    private route: Router,
    private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.process = this.activatedRoute.snapshot.data['process'];
    this.criateForm();
    this.findAllUSers();
  }

  public criateForm() {
      this.formAssign = this.formBuilder.group({
          code:   [{value: '', disabled: true}, Validators ],
          name:   [{value: '', disabled: true}],
          status: [{value: '', disabled: true}],
          users: []
      });


      this.formAssign.patchValue(this.process);
  }

  public findAllUSers() {
    this.userService.findAll().subscribe(users => {
      this.users = users;
    });
  }

  public assign() {
    const users: User[] = this.formAssign.controls['users'].value;

    this.process.users = [];
    users.map(user => {
        this.process.users.push(user);
    });

    this.processService.update(this.process).subscribe(respose => {
        this.massagesServer.success('Processo atribuído com sucesso', 'Sucesso');
        this.route.navigate([`triagem/${this.process.id}/show`]);
    });
  }

}
