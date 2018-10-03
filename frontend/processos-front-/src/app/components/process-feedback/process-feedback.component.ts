import { ProcessFeedback } from './../../model/ProcessFeedback';
import { ProcessFeedbackService } from './../../services/process/process-feedback.service';
import { Process } from './../../model/Process';
import { ResponseApi } from './../../model/response-api';
import { NgForm } from '@angular/forms';
import { UserService } from './../../services/user/user.service';
import { SharedService } from './../../services/shared.service';
import { Router, ActivatedRoute } from '@angular/router';
import { ProcessService } from './../../services/process/process.service';
import { DialogService } from './../../dialog.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { User } from '../../model/user';

@Component({
  selector: 'app-process-feedback',
  templateUrl: './process-feedback.component.html',
  styleUrls: ['./process-feedback.component.css']
})
export class ProcessFeedbackComponent implements OnInit {

  @ViewChild("form")
  form: NgForm;

  processFeedback = new ProcessFeedback(
      '',
      '',
      new Process('','',''),
      null);
  message : {};
  classCss : {};
  shared : SharedService;
  finalizators=[]; 
  finalizatorsAdd=[]; 
  page:number=0;
  count:number=5;
  pages:Array<number>;


  constructor(
    private dialogService: DialogService,
    private processService: ProcessService,
    private processFeedbackService: ProcessFeedbackService,
    private userService: UserService,
    private route: ActivatedRoute,
    private router: Router) { 
      this.shared = SharedService.getInstance();
  }

  ngOnInit() {
    this.chargeFinalizator();
    let idProcess:string = this.route.snapshot.params['idProcess'];
    if(idProcess != undefined && idProcess != ''){
      this.chargeProcess(idProcess);
    }
    this.chargeAdd(this.page, this.count, idProcess);
  }

  chargeFinalizator(){
    this.userService.findByProfile('ROLE_FINALIZADOR').subscribe((responseApi:ResponseApi) => {
      this.finalizators = responseApi['data'];
    } , err => {
      this.showMessage({
        type: 'error',
        text: err['error']['errors'][0]
      });
    });
  }

  chargeProcess(id:string){
    this.processService.findById(id).subscribe((responseApi:ResponseApi) => {
      this.processFeedback.process = responseApi.data;
    } , err => {
      this.showMessage({
        type: 'error',
        text: err['error']['errors'][0]
      });
    });
  }

  chargeAdd(page:number,count:number, id:string){
    this.processFeedbackService.findByProcess(page,count, id).subscribe((responseApi:ResponseApi) => {
        this.finalizatorsAdd = responseApi['data']['content'];
        this.pages = new Array(responseApi['data']['totalPages']);
    } , err => {
      this.showMessage({
        type: 'error',
        text: err['error']['errors'][0]
      });
    });
  }

  addFinalizator(){
    this.message = {};
    this.processFeedbackService.createOrUpdate(this.processFeedback).subscribe((responseApi:ResponseApi) => {
      let process : ProcessFeedback = responseApi.data;  
      this.processFeedback = new ProcessFeedback(
          '',
          '',
          process.process,
          new User('', '', '', ''));

        this.form.resetForm();
        this.showMessage({
          type: 'success',
          text: `Registered ${process.finalizator.email} successfully`
        });
        this.chargeAdd(this.page, this.count, process.process.id);
    } , err => {
      this.showMessage({
        type: 'error',
        text: err['error']['errors'][0]
      });
    });
  }

  private showMessage(message: {type: string, text: string}): void {
    this.message = message;
    this.buildClasses(message.type);
    setTimeout(() => {
      this.message = undefined;
    }, 3000);
  }

  private buildClasses(type: string): void {
    this.classCss = {
      'alert': true
    }
    this.classCss['alert-'+type] =  true;
  }

  getFormGroupClass(isInvalid: boolean, isDirty:boolean): {} {
    return {
      'form-group': true,
      'has-error' : isInvalid  && isDirty,
      'has-success' : !isInvalid  && isDirty
    };
  }
  
  setNextPage(event:any){
    event.preventDefault();
    if(this.page+1 < this.pages.length){
      this.page =  this.page +1;
      this.chargeAdd(this.page,this.count, this.processFeedback.process.id);
    }
  }

  setPreviousPage(event:any){
    event.preventDefault();
    if(this.page > 0){
      this.page =  this.page - 1;
      this.chargeAdd(this.page,this.count, this.processFeedback.process.id);
    }
  }

  setPage(i,event:any){
    event.preventDefault();
    this.page = i;
    this.chargeAdd(this.page,this.count, this.processFeedback.process.id);
  }

}
