import { ProcessFeedbackService } from './../../services/process/process-feedback.service';
import { ResponseApi } from './../../model/response-api';
import { ActivatedRoute } from '@angular/router';
import { ProcessService } from './../../services/process/process.service';
import { Process } from './../../model/Process';
import { SharedService } from './../../services/shared.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-process-detail',
  templateUrl: './process-detail.component.html',
  styleUrls: ['./process-detail.component.css']
})
export class ProcessDetailComponent implements OnInit {
  process = new Process('','','');
  shared : SharedService;
  message : {};
  classCss : {};
  listProcess=[]; 
  page:number=0;
  count:number=5;
  pages:Array<number>;

  constructor(
    private processService: ProcessService,
    private processFeedbackService: ProcessFeedbackService,
    private route: ActivatedRoute) { 
      this.shared = SharedService.getInstance();
  }

  ngOnInit() {
    let id:string = this.route.snapshot.params['idProcess'];
    if(id != undefined && id != ''){
      this.findById(id);
      this.findAllFeedback(this.page, this.count, id);
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

  findAllFeedback(page:number,count:number, id:string){
    this.processFeedbackService.findByProcess(page,count, id).subscribe((responseApi:ResponseApi) => {
        this.listProcess = responseApi['data']['content'];
        this.pages = new Array(responseApi['data']['totalPages']);
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

}
