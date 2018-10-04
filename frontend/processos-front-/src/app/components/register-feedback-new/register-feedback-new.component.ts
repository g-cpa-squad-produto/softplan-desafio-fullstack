import { NgForm } from '@angular/forms';
import { ResponseApi } from './../../model/response-api';
import { SharedService } from './../../services/shared.service';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import { ProcessFeedbackService } from './../../services/process/process-feedback.service';
import { DialogService } from './../../dialog.service';
import { ProcessFeedback } from './../../model/ProcessFeedback';
import { Component, OnInit, ViewChild } from '@angular/core';

@Component({
  selector: 'app-register-feedback-new',
  templateUrl: './register-feedback-new.component.html',
  styleUrls: ['./register-feedback-new.component.css']
})
export class RegisterFeedbackNewComponent implements OnInit {

  @ViewChild("form")
  form: NgForm;

  processFeedback = new ProcessFeedback(
    '',
    '',
    null,
    null);
    shared : SharedService;
    message : {};
    classCss : {};

    constructor(
      private dialogService: DialogService,
      private processFeedbackService: ProcessFeedbackService,
      private route: ActivatedRoute,
      private router: Router) { 
        this.shared = SharedService.getInstance();
    }

  ngOnInit() {
    let id:string = this.route.snapshot.params['idProcessFeedback'];
    console.log(id);
    if(id != undefined && id != ''){
      this.findById(id);
    }
  }

  findById(id:string){
    this.processFeedbackService.findById(id).subscribe((responseApi:ResponseApi) => {
      this.processFeedback = responseApi.data;
  } , err => {
    this.showMessage({
      type: 'error',
      text: err['error']['errors'][0]
    });
  });
  }

  register(){
    this.message = {};
    this.processFeedbackService.createOrUpdate(this.processFeedback).subscribe((responseApi:ResponseApi) => {
        this.processFeedback = new ProcessFeedback(
          '',
          '',
          null,
          null);
        let process : ProcessFeedback = responseApi.data;
        this.form.resetForm();
        this.showMessage({
          type: 'success',
          text: `Registered feedback successfully`
        });
        this.router.navigate(['/register-feedback']);
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

}
