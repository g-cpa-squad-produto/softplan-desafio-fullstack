import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { ProcessService } from '../../../services/process/process.service';
import { Process } from '../../../services/process/process.model';
import { LegalAdvice } from '../../../services/legal.advice/legal.advice.model';

@Component({
  selector: 'app-form-process',
  templateUrl: './form.process.component.html',
  styleUrls: ['./form.process.component.scss']
})
export class FormProcessComponent implements OnInit {

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private processService: ProcessService
  ) { }

  processId: number;
  process: Process = new Process();
  legalAdvice: LegalAdvice = new LegalAdvice();
  listLegalAdvice: LegalAdvice[] = new Array<LegalAdvice>();

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      if(params.get('id')){
        this.processId = Number(params.get('id'))
        this.processService.getProcessById(this.processId).subscribe(
          p =>{
              this.process = p;
          }
        )
      }else{
        this.process = new Process();
      }
    });
  }

  addLegalAdvice(){

    let newListLegalAdvice = this.listLegalAdvice.slice(0);
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

  createProcess(){
    this.process.idCreatedBy = 9999991;
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
