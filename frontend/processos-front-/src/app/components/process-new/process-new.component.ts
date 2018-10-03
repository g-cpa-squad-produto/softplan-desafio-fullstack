import { ResponseApi } from './../../model/response-api';
import { ActivatedRoute } from '@angular/router';
import { Process } from './../../model/process';
import { SharedService } from './../../services/shared.service';
import { NgForm } from '@angular/forms';
import { ProcessService } from './../../services/process/process.service';
import { Component, OnInit, ViewChild } from '@angular/core';

@Component({
  selector: 'app-process-new',
  templateUrl: './process-new.component.html',
  styleUrls: ['./process-new.component.css']
})
export class ProcessNewComponent implements OnInit {

  @ViewChild("form")
  form: NgForm;

  process = new Process('','','');
  shared : SharedService;
  message : {};
  classCss : {};

  constructor(
    private processService: ProcessService,
    private route: ActivatedRoute) { 
      this.shared = SharedService.getInstance();
  }

  ngOnInit() {
    let id:string = this.route.snapshot.params['id'];
    if(id != undefined && id != ''){
      this.findById(id);
    }
  }

  findById(id:string){
    this.processService.findById(id).subscribe((responseApi:ResponseApi) => {
      this.process = responseApi.data;
  } , err => {
    this.showMessage({
      type: 'error',
      text: err['error']['errors'][0]
    });
  });
  }

  register(){
    this.message = {};
    this.processService.createOrUpdate(this.process).subscribe((responseApi:ResponseApi) => {
        this.process = new Process('','','');
        let process : Process = responseApi.data;
        this.form.resetForm();
        this.showMessage({
          type: 'success',
          text: `Registered ${process.number} successfully`
        });
    } , err => {
      this.showMessage({
        type: 'error',
        text: err['error']['errors'][0]
      });
    });
  }

  getFormGroupClass(isInvalid: boolean, isDirty:boolean): {} {
    return {
      'form-group': true,
      'has-error' : isInvalid  && isDirty,
      'has-success' : !isInvalid  && isDirty
    };
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

}
