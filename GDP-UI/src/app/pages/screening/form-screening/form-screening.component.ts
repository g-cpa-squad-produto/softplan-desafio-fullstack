import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { MassegesService } from 'src/app/core/messeges/messages.service';
import { UserService } from 'src/app/core/service/user.service';
import { FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-form-screening',
  templateUrl: './form-screening.component.html',
  styleUrls: ['./form-screening.component.css']
})
export class FormScreeningComponent implements OnInit {

  public id: number;

  constructor( private formBuilder: FormBuilder,
    private activeRoute: ActivatedRoute,
    private massagesServer: MassegesService,
    private userService: UserService,
    private route: Router) { }

  ngOnInit() {
  }

}
