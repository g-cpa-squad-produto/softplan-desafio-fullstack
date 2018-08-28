import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { LegalAdviceService } from '../../../services/legal.advice/legal.advice.service';
import { LegalAdvice } from '../../../services/legal.advice/legal.advice.model'

@Component({
  selector: 'app-form-legal-advice',
  templateUrl: './form.legal.advice.component.html',
  styleUrls: ['./form.legal.advice.component.scss']
})
export class FormLegalAdviceComponent implements OnInit {

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private legalAdviceService: LegalAdviceService
  ) { }

  legalAdviceId: number;
  legalAdvice: LegalAdvice = new LegalAdvice();

  user: string;
  private users: string[] = ['asdasd', 'asdfasd', 'bhjfj', 'sdf sthd','asdasd', 'asdfasd', 'bhjfj', 'sdf sthd'];

  ngOnInit() {
    /*
    this.route.paramMap.subscribe(params => {
      if(params.get('id')){
        this.legalAdviceId = Number(params.get('id'))
        this.legalAdviceService.getLegalAdviceById(this.legalAdviceId).subscribe(
          l =>{
              this.legalAdvice = l;
          }
        )
      }else{
        this.legalAdvice = new LegalAdvice();
      }
    });
    */
  }

  save(){
    if(this.legalAdvice.id){
      this.upDateProcess();
    }else{
      this.createProcess();
    }
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
