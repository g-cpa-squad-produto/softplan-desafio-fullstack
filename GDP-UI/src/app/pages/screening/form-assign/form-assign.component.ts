import { ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/user';
import { Process } from 'src/app/model/process';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MassegesService } from 'src/app/core/messeges/messages.service';

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
    private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.process = this.activatedRoute.snapshot.data['process'];
  }


  public assign() {

  }

}
