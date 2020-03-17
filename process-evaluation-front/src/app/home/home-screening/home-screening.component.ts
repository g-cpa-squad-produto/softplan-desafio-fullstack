import { Component, OnInit } from '@angular/core';
import { Page } from 'src/app/shared/page';
import { Process } from 'src/app/process/process';
import { ProcessService } from 'src/app/process/process.service';
import { Router } from '@angular/router';
import { faEdit } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'process-home-screening',
  templateUrl: './home-screening.component.html'
})
export class HomeScreeningComponent implements OnInit {

  processPage: Page<Process> = new Page();
  page: number = 1;
  previousPage: number;
  maxRecords: number = 3;
  faIcon = faEdit;

  constructor(private processService: ProcessService, 
              private router: Router) { }

  ngOnInit() {
    this.findProcessList();
  }
  
  private findProcessList() {
    this.processService.findAll(this.page - 1, this.maxRecords).subscribe(
      res => this.processPage = res
    );
  }

  loadPage(page: number) {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.findProcessList();  
    }
  }

  newProcess() {
    this.router.navigate(['/process']);
  }

  show(idProcess: number) {
    this.router.navigate(['/process', idProcess]);
  }
}