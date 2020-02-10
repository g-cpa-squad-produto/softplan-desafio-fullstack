import {Component, OnInit} from '@angular/core';
import {Report} from '../../../shared/model/report.model';
import {ReportService} from '../../../services/report.service';

@Component({
  selector: 'app-report-list',
  templateUrl: './report-list.component.html'
})
export class ReportListComponent implements OnInit {

  reports: Report[];
  message: {};
  classCss: {};

  constructor(
    private reportService: ReportService) {
  }

  ngOnInit() {
    this.findAll();
  }

  private findAll() {
    this.reportService.findAllByAuthor().subscribe(
      (reports: Report[]) => this.reports = reports,
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
}
