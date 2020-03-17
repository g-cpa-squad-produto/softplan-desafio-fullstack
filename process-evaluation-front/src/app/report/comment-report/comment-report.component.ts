import { Component, OnInit, Input } from '@angular/core';
import { ReportService } from '../report.service';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'process-comment-report',
  templateUrl: './comment-report.component.html'
})
export class CommentReportComponent implements OnInit {

  formReport: FormGroup;
  @Input() idProcess: number;
  
  constructor(private reportService: ReportService, 
              public activeModal: NgbActiveModal, 
              private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.formReport = this.formBuilder.group({
      text: ['', [Validators.required, Validators.maxLength(256)]] 
    })
  }

  save() {
    const text = this.formReport.get('text').value;
    this.reportService.addTextToReport(this.idProcess, text).subscribe(
      () => {
        this.formReport.reset();
        this.activeModal.close('OK');
      },
      (error) => this.activeModal.close(error.error[0].mensagemUsuario)
    );
  }
}