import { Component, OnInit } from '@angular/core';
import { ParecerService } from '../parecer.service';
import { Processo } from 'src/app/processo/processo.entity';
import { Router } from '@angular/router';

@Component({
  selector: 'app-parecer-listagem',
  templateUrl: './parecer-listagem.component.html',
  styleUrls: ['./parecer-listagem.component.css']
})
export class ParecerListagemComponent implements OnInit {

  processosSemParecer: Processo[] = new Array();
  constructor(private parecerService: ParecerService,
              private router: Router) { }

  ngOnInit() {
    this.parecerService.listProcessosSemParecer().subscribe(res => this.processosSemParecer = res);
  }

  adicionarParecer(processoId: string) {
    this.router.navigate(['./parecer/adicionar', processoId]);
  }
}
