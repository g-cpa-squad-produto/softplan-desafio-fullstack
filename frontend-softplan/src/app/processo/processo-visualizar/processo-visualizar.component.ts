import { Component, OnInit } from '@angular/core';
import { ResponseModel } from 'src/app/response/response.entity';
import { ProcessoService } from '../processo.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Processo } from '../processo.entity';
import { ParecerService } from 'src/app/parecer/parecer.service';

@Component({
  selector: 'app-processo-visualizar',
  templateUrl: './processo-visualizar.component.html',
  styleUrls: ['./processo-visualizar.component.css']
})
export class ProcessoVisualizarComponent implements OnInit {

  processo: Processo = new Processo();
  private response: ResponseModel = new ResponseModel();
  possuiParecer: Boolean;
  constructor(private processoService: ProcessoService,
              private parecerService: ParecerService,
              private router: Router,
              private activatedRouter: ActivatedRoute) { }

  ngOnInit() {
    this.activatedRouter.params.subscribe(parameters => {
      this.processoService.getProcessoById(parameters['id'])
          .subscribe(proc => {
            this.processo = proc;
            this.parecerService.getParecerByProcesso(parameters['id'])
              .subscribe(par => this.processo.parecer = par);
          });
          alert(this.processo.parecer);
        this.possuiParecer = this.processo.parecer ? true : false;
    });
  }

  editar() {
    this.router.navigate(['./processo/editar', this.processo.id]);
  }

  voltar() {
    this.router.navigate(['./processo']);
  }
}
