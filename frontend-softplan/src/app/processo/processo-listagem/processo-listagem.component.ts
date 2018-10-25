import { Component, OnInit } from '@angular/core';
import { Processo } from '../processo.entity';
import { ProcessoService } from '../processo.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-processo-listagem',
  templateUrl: './processo-listagem.component.html',
  styleUrls: ['./processo-listagem.component.css']
})
export class ProcessoListagemComponent implements OnInit {

  processos: Processo[] = new Array();

  constructor(private processoService: ProcessoService,
              private router: Router) { }

  ngOnInit() {
    this.processoService.listarTodos().subscribe(res => this.processos = res);
  }

  editar(id: string) {
    this.router.navigate(['./processo/editar', id]);
  }

  visualizar(id: string) {
    this.router.navigate(['./processo/visualizar', id]);
  }
}
