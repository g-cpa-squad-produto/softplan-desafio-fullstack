import { ResponseApi } from './../../model/response-api';
import { ActivatedRoute, Router } from '@angular/router';
import { ProcessFeedbackService } from './../../services/process/process-feedback.service';
import { DialogService } from './../../dialog.service';
import { SharedService } from './../../services/shared.service';
import { ProcessFeedback } from './../../model/ProcessFeedback';
import { NgForm } from '@angular/forms';
import { Component, OnInit, ViewChild } from '@angular/core';
import { Process } from '../../model/Process';

@Component({
  selector: 'app-register-feedback',
  templateUrl: './register-feedback.component.html',
  styleUrls: ['./register-feedback.component.css']
})
export class RegisterFeedbackComponent implements OnInit {

  @ViewChild("form")
  form: NgForm;

  message : {};
  classCss : {};
  shared : SharedService;
  listProcess=[]; 
  page:number=0;
  count:number=5;
  pages:Array<number>;


  constructor(
    private dialogService: DialogService,
    private processFeedbackService: ProcessFeedbackService,
    private route: ActivatedRoute,
    private router: Router) { 
      this.shared = SharedService.getInstance();
  }

  ngOnInit() {
    this.findAll(this.page, this.count, this.shared.user.id);
  }

  findAll(page:number,count:number, id:string){
    this.processFeedbackService.findByFinalizator(page,count, id, true).subscribe((responseApi:ResponseApi) => {
        this.listProcess = responseApi['data']['content'];
        this.pages = new Array(responseApi['data']['totalPages']);
    } , err => {
      this.showMessage({
        type: 'error',
        text: err['error']['errors'][0]
      });
    });
  }

  registerFeedback(id:string){
    this.router.navigate(['/register-feedback-new',id]);
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
      this.findAll(this.page, this.count, this.shared.user.id);
    }
  }

  setPreviousPage(event:any){
    event.preventDefault();
    if(this.page > 0){
      this.page =  this.page - 1;
      this.findAll(this.page, this.count, this.shared.user.id);
    }
  }

  setPage(i,event:any){
    event.preventDefault();
    this.page = i;
    this.findAll(this.page, this.count, this.shared.user.id);
  }

}
