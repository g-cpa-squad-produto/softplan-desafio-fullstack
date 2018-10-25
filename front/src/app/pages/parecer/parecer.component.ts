import { Component, OnInit, TemplateRef } from '@angular/core';
import { ProcessosService } from 'src/app/service/processos/processos.service';
import { ParecerService } from 'src/app/service/parecer/parecer.service';
import { Processo } from 'src/app/modelos/processo';
import { Parecer } from 'src/app/modelos/parecer';
import { BsModalService, BsModalRef } from 'ngx-bootstrap';

@Component({
  selector: 'app-parecer',
  templateUrl: './parecer.component.html',
  styleUrls: ['./parecer.component.css'],
  providers: [ProcessosService, ParecerService]
})
export class ParecerComponent implements OnInit {

  public processos: [Processo];
  public pareceres: [Parecer];
  public parecerACadastrar: Parecer = new Parecer();
  public modalRef: BsModalRef;

  constructor(public modalService: BsModalService, public processoService: ProcessosService, public parecerService: ParecerService, ) { }

  ngOnInit() {
    this.listarProcessosSemParecer();
  }

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template, { backdrop: 'static', keyboard: false });
  }
  listarProcessosSemParecer() {
    this.processoService.listarProcessosSemParecer().toPromise().then(
      data => {
        this.processos = data.data;
      }
    )
  }
  escolherEste(processo: Processo) {
    this.parecerACadastrar.processo = processo;
  }

  cadastrarParecer(parecer: Parecer) {
    if (parecer.nome != null) {
      this.parecerService.cadastrar(parecer).toPromise().then(

        data => {
          this.parecerACadastrar = new Parecer();
          this.listarProcessosSemParecer()
        }
      )
    }
  }

 
}
