import { Process } from './../../model/process';
import { ResponseApi } from './../../model/response-api';
import { SharedService } from './../../services/shared.service';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { DialogService } from '../../dialog.service';
import { ProcessService } from '../../services/process/process.service';

@Component({
  selector: 'app-process-list',
  templateUrl: './process-list.component.html',
  styleUrls: ['./process-list.component.css']
})
export class ProcessListComponent implements OnInit {

  assignedToMe: boolean = false;
  page:number=0;
  count:number=5;
  pages:Array<number>;
  shared : SharedService;
  message : {};
  classCss : {};
  listProcess=[];
  processFilter = new Process('','','');

  constructor(
    private dialogService: DialogService,
    private processService: ProcessService,
    private router: Router) { 
      this.shared = SharedService.getInstance();
  }

  ngOnInit() {
    this.findAll(this.page,this.count);
  }

  findAll(page:number,count:number){
    this.processService.findAll(page,count).subscribe((responseApi:ResponseApi) => {
        this.listProcess = responseApi['data']['content'];
        this.pages = new Array(responseApi['data']['totalPages']);
    } , err => {
      this.showMessage({
        type: 'error',
        text: err['error']['errors'][0]
      });
    });
  }

  filter(): void {
    console.log(' this.assignedToMe --> ',this.assignedToMe);
    this.page = 0;
    this.count = 5;
    this.processService.findByParams(this.page,this.count,this.processFilter)
    .subscribe((responseApi:ResponseApi) => {
      this.processFilter.number = this.processFilter.number == 'uninformed' ? null : this.processFilter.number;  
      this.listProcess = responseApi['data']['content'];
        this.pages = new Array(responseApi['data']['totalPages']);
    } , err => {
      this.showMessage({
        type: 'error',
        text: err['error']['errors'][0]
      });
    });
  }

  cleanFilter(): void {
    this.assignedToMe = false;
    this.page = 0;
    this.count = 5;
    this.processFilter = new Process('','','');
    this.findAll(this.page,this.count);
  }


  edit(id:string){
    this.router.navigate(['/process-new',id]);
  }
  
  addFinalizator(id:string){
    this.router.navigate(['/process-feedback',id]);
  }

  detail(id:string){
    this.router.navigate(['/process-detail',id]);
  }

  delete(id:string){
    this.dialogService.confirm('Do you want to delete the process ?')
      .then((candelete:boolean) => {
          if(candelete){
            this.message = {};
            this.processService.delete(id).subscribe((responseApi:ResponseApi) => {
                this.showMessage({
                  type: 'success',
                  text: `Record deleted`
                });
                this.findAll(this.page,this.count);
            } , err => {
              this.showMessage({
                type: 'error',
                text: err['error']['errors'][0]
              });
            });
          }
      });
  }

  setNextPage(event:any){
    event.preventDefault();
    if(this.page+1 < this.pages.length){
      this.page =  this.page +1;
      this.findAll(this.page,this.count);
    }
  }

  setPreviousPage(event:any){
    event.preventDefault();
    if(this.page > 0){
      this.page =  this.page - 1;
      this.findAll(this.page,this.count);
    }
  }

  setPage(i,event:any){
    event.preventDefault();
    this.page = i;
    this.findAll(this.page,this.count);
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
