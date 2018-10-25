import { Component, OnInit } from '@angular/core';
import { ParecerService } from '../parecer.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Processo } from 'src/app/processo/processo.entity';
import { ResponseModel } from 'src/app/response/response.entity';
import { Parecer } from '../parecer.entity';

@Component({
  selector: 'app-parecer-create',
  templateUrl: './parecer-create.component.html',
  styleUrls: ['./parecer-create.component.css']
})
export class ParecerCreateComponent implements OnInit {

  processo: Processo = new Processo();
  parecer: Parecer = new Parecer();
  response: ResponseModel = new ResponseModel();

  constructor(private parecerService: ParecerService,
              private activatedRoute: ActivatedRoute,
              private router: Router) { }

  ngOnInit() {
    this.activatedRoute.params.subscribe(parameters => {
      this.parecerService.getProcessoById(parameters['processoId'])
        .subscribe(proc => this.processo = proc);
    });
  }

  voltar() {
    this.router.navigate(['./parecer']);
  }

  salvarParecer() {
    this.processo.parecer = this.parecer;
    this.parecerService.salvar(this.processo).subscribe(res => {
      this.response = <ResponseModel>res;
      alert(this.response.mensagem);
      if (this.response.id === 1) {
        this.voltar();
      }
    });
  }
}
