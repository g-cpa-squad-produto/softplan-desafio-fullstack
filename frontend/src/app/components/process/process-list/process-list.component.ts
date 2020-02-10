import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {ProcessService} from '../../../services/process.service';
import {Process} from '../../../shared/model/process.model';

@Component({
  selector: 'app-process-list',
  templateUrl: './process-list.component.html'
})
export class ProcessListComponent implements OnInit {

  processes: Process[];
  percent: number[];
  message: {};
  classCss: {};

  constructor(
    private processService: ProcessService,
    private router: Router
  ) {
  }

  ngOnInit() {
    this.findAll();
  }

  private findAll() {
    this.processService.findAllByAuthor().subscribe(
      (processes: Process[]) => {
        this.processes = processes;
        this.calculatePercent(processes);
      },
      error => this.showMessage({
        type: 'error',
        text: 'Servico indisponível'
      }));
  }

  private showMessage(message: { type: string, text: string }) {
    this.message = message;
    this.buildClasses(message.type);
    setTimeout(() => {
      this.message = undefined;
    }, 3000);
  }

  private buildClasses(type: string) {
    this.classCss = {
      alert: true
    };
    this.classCss[`alert-${type}`] = true;
  }

  private calculatePercent(processes: Process[]) {
    this.percent = [];
    processes.forEach((process) => {
      let done = 0;
      process.reports.forEach((report) => {
        if (report.status.toString() === 'CONCLUIDO') {
          done++;
        }
      });
      this.percent.push(done / process.reports.length);
    });
  }

}
