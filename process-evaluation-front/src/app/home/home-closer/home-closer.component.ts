import { Component } from '@angular/core';
import { Process } from 'src/app/process/process';
import { ProcessService } from 'src/app/process/process.service';
import { faCommentDots } from '@fortawesome/free-solid-svg-icons';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { CommentReportComponent } from 'src/app/report/comment-report/comment-report.component';
import { ToastService } from 'src/app/shared/toast/toast.service';

@Component({
  selector: 'process-home-closer',
  templateUrl: './home-closer.component.html'
})
export class HomeCloserComponent {

  processList: Process[] = [];  
  faIcon = faCommentDots;

  constructor(private processService: ProcessService, 
              private modalService: NgbModal,
              private toastService: ToastService) { }

  ngOnInit() {
    this.findProcessList();
  }

  private findProcessList() {
    this.processService.findMyPendings().subscribe(
      res => this.processList = res
    );
  }

  addComment(idProcess: number) {
    const modalRef = this.modalService.open(CommentReportComponent);
    modalRef.componentInstance.idProcess = idProcess;
    modalRef.result.then(
      () => {
        this.toastService.showSuccess('Parecer submetido com sucesso!');
        this.findProcessList();
      },
      reason => this.toastService.showError(reason)
    )
  }

}