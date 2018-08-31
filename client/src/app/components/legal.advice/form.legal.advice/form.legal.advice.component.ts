import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { LegalAdviceService } from '../../../services/legal.advice/legal.advice.service';
import { LegalAdvice } from '../../../services/legal.advice/legal.advice.model';

import { UserService } from '../../../services/user/user.service';
import { User } from '../../../services/user/user.model';

@Component({
  selector: 'app-form-legal-advice',
  templateUrl: './form.legal.advice.component.html',
  styleUrls: ['./form.legal.advice.component.scss']
})
export class FormLegalAdviceComponent implements OnInit {

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private legalAdviceService: LegalAdviceService,
    private userService: UserService
  ) { }

  legalAdviceId: number;
  legalAdvice: LegalAdvice = new LegalAdvice();

  users: User[];

  ngOnInit() {

    this.route.paramMap.subscribe(params => {
      if(params.get('id')){
        this.legalAdviceId = Number(params.get('id'))
        this.legalAdviceService.getLegalAdviceById(this.legalAdviceId).subscribe(
          p =>{
            console.log('getProcessById==>', p)
            this.legalAdvice = p;
          }
        )
      }else{
        this.legalAdvice = new LegalAdvice();
      }
    });

    this.userService.getAllUsers()
      .subscribe(res => {
        console.log('>>>> get users res=', res);
        this.users = res; 
    },
    error => {
      console.log('error service get users ==>', error);
    });
  }

  save(){
    if(this.legalAdvice.id){
      this.upDateProcess();
    }else{
      this.createProcess();
    }
  }

  redirectProcess(){
    this.router.navigate([`/process/form/${this.legalAdvice.processId}`])
  }

  createProcess(){
    this.legalAdvice.idCreatedBy = 9999991;
    this.legalAdviceService.createLegalAdvice(this.legalAdvice).subscribe(
      u =>{
        this.router.navigate(['/legalAdvice'])
      },
      err =>{
        console.error(err);
      });
  }

  upDateProcess(){
    this.legalAdviceService.updateLegalAdvice(this.legalAdviceId, this.legalAdvice).subscribe(
      p =>{
        this.router.navigate(['/legalAdvice'])
      },
      err =>{
        console.error(err);
      });
  }

  voltar() {
    window.history.back();
  }

}
