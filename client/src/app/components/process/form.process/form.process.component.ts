import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { ProcessService } from '../../../services/process/process.service';
import { Process } from '../../../services/process/process.model';
import { LegalAdvice } from '../../../services/legal.advice/legal.advice.model';

import { UserService } from '../../../services/user/user.service';
import { User } from '../../../services/user/user.model';

@Component({
  selector: 'app-form-process',
  templateUrl: './form.process.component.html',
  styleUrls: ['./form.process.component.scss']
})
export class FormProcessComponent implements OnInit {

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private processService: ProcessService,
    private userService: UserService
  ) { }

  processId: number;
  process: Process = new Process();
  legalAdvice: LegalAdvice = new LegalAdvice();
  listLegalAdvice: LegalAdvice[] = new Array<LegalAdvice>();

  users: User[];

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      if(params.get('id')){
        this.processId = Number(params.get('id'))
        this.processService.getProcessById(this.processId).subscribe(
          p =>{
            console.log('getProcessById==>', p)
            this.process = p;
            this.listLegalAdvice = p.legalAdvices;
          }
        )
      }else{
        this.process = new Process();
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

  addLegalAdvice(){
    let tempUser:User = this.users.find(l=> l.id == this.legalAdvice.idResponsableFor);
    console.log('tempUser',tempUser);
    this.legalAdvice.loginResponsableFor = tempUser.login;
    this.legalAdvice.nameResponsableFor = tempUser.name;
    let newListLegalAdvice: LegalAdvice[] = this.listLegalAdvice.slice(0);
    this.legalAdvice.idCreatedBy = 9999991;
    newListLegalAdvice.push(this.legalAdvice);
    this.listLegalAdvice = newListLegalAdvice;
    this.legalAdvice = new LegalAdvice();
  }

  save(){
    if(this.process.id){
      this.upDateProcess();
    }else{
      this.createProcess();
    }
  }

  redirectLegalAdvice(id){
    this.router.navigate([`/legalAdvice/form/${id}`])
  }

  createProcess(){
    this.process.idUserCreatedBy = 9999991;
    this.process.legalAdvices = this.listLegalAdvice;
    this.processService.createProcess(this.process).subscribe(
      u =>{
        this.router.navigate(['/process'])
      },
      err =>{
        console.error(err);
      });
  }

  upDateProcess(){
    this.processService.updateProcess(this.processId, this.process).subscribe(
      p =>{
        this.router.navigate(['/process'])
      },
      err =>{
        console.error(err);
      });
  }

  voltar() {
    window.history.back();
  }

}
