import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ProcessService } from './process.service';
import { Process } from './process';
import { ToastService } from '../shared/toast/toast.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Report } from '../report/report';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { UserReportComponent } from '../report/user-report/user-report.component';

@Component({
  selector: 'process-form',
  templateUrl: './process.component.html'
})
export class ProcessComponent implements OnInit {

  formProcess: FormGroup;
  reports: Report[] = [];
  update: boolean = false;

  constructor(private formBuilder: FormBuilder, 
              private processService: ProcessService,
              private toastService: ToastService,
              private router: Router,
              private modalService: NgbModal,
              private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.formProcess = this.formBuilder.group({
      idProcess: '',
      title: ['', [Validators.required, Validators.maxLength(20)]],
      description: ['', [Validators.required, Validators.maxLength(256)]],
    });

    if (this.activatedRoute.snapshot.params.id) {
      const id = this.activatedRoute.snapshot.params.id;
      this.loadById(id);
    }
  }

  private loadById(id) {
    this.processService.findById(id).subscribe(
      res => {     
        this.update = true;   
        this.reports = [...res.reports];
        this.formProcess.patchValue(res);
        console.log(this.reports);
        this.formProcess.disable();
      }
    )
  }

  save() {
    let process = this.formProcess.getRawValue() as Process;
    process.reports = [...this.reports];
    
    this.processService.insert(process).subscribe(
      () => {
        this.toastService.showSuccess('Processo salvo com sucesso!');
        this.formProcess.reset();
        this.router.navigate(['/home']);
      },
      (error) => this.toastService.showError(error.error[0].mensagemUsuario)
    )
  }

  addUser() {
    const modalRef = this.modalService.open(UserReportComponent);
    modalRef.componentInstance.addeds = this.reports;
    modalRef.result.then(
      result => {
        let report = new Report();
        report.author = result.idUser;
        report.name = result.name;    
        if (this.formProcess.get('idProcess').value) {
          report.process = this.formProcess.get('idProcess').value;
        }
        this.reports = this.reports.concat(report);
      },
      reason => console.log('Fechou')
    )
  }

}