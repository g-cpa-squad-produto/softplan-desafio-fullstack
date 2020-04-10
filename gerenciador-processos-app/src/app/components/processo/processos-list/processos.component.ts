import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ProcessoService} from '../processo.service';
import {Processo} from '../../../model/processo.model';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {ModalComponent} from '../../../modal/modal.component';

@Component({
  selector: 'app-processos',
  templateUrl: './processos.component.html',
  styleUrls: ['./processos.component.css']
})
export class ProcessosComponent implements OnInit {

  @Output() setProcesso = new EventEmitter<Processo>();
  @Input() processo: Processo;
  processos: Processo[];
  p = 1;

  constructor(private processoService: ProcessoService, private modalService: NgbModal) {
    processoService.getProcessos().subscribe(processos => this.processos = processos);
  }
  ngOnInit(): void {
  }
  open(processo: Processo) {
    const modalRef = this.modalService.open(ModalComponent);
    modalRef.componentInstance.title = 'Remover';
    modalRef.componentInstance.message = 'Tem certeza que deseja remover o processo?';
    modalRef.componentInstance.button1 = 'Sim';
    modalRef.componentInstance.button2 = 'Não';
    this.emitSetProcesso(processo);
  }

  emitSetProcesso(processo: Processo) {
    this.setProcesso.emit(processo);
  }

}
