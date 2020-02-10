import {Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';
import {Process} from '../../../shared/model/process.model';
import {ActivatedRoute} from '@angular/router';
import {Report} from '../../../shared/model/report.model';
import {ReportService} from '../../../services/report.service';

@Component({
  selector: 'app-report-new',
  templateUrl: './report-new.component.html'
})
export class ReportNewComponent implements OnInit {

  editForm = this.fb.group({
    id: [],
    description: [null, [Validators.required]],
    status: [],
  });
  report: Report;
  message: { type: string, text: string };
  classCss: {};

  constructor(
    private reportService: ReportService,
    private route: ActivatedRoute,
    private fb: FormBuilder) { }

  ngOnInit() {
    const id: number = this.route.snapshot.params.id;
    if (id !== undefined) {
      this.findById(id);
    }
  }

  updateForm(process: Process): void {
    this.editForm.patchValue({
      id: process.id,
      title: process.title,
      description: process.description,
      autor: process.autor
    });
  }
  previousState(): void {
    window.history.back();
  }

  private findById(id: number) {
    this.reportService.find(id).subscribe((report: Report) => {
      this.editForm.patchValue(report);
      this.report = report;
    });
  }

  register() {
    this.message = {type: '', text: ''};
    this.update(this.createFromForm());
  }

  private createFromForm(): Report {
    const report = new Report();
    report.description = this.editForm.get(['description'])!.value;
    report.status = this.editForm.get(['status'])!.value;
    report.id = this.report.id;
    return report;
  }

  private update(reportForm: Report) {
    this.reportService.update(reportForm).subscribe((report: Report) => {
      this.showMessage({
        type: 'success',
        text: 'Relatório alterado com sucesso'
      })
    });
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
