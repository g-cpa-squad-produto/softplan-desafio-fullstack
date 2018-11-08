import { Usuario } from './../../../../entidades/usuario';
import { Location } from '@angular/common';
import { AdmComponent } from './../../../../adm/adm.component';
import { ProcessoService } from './../../../../servicos/processo.service';
import { Router } from '@angular/router';
import { Processo } from './../../../../entidades/processo';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-list-pendente',
  templateUrl: './list-pendente.component.html',
  styleUrls: ['./list-pendente.component.css']
})
export class ListPendenteComponent implements OnInit {

  lstProcessosPendentes = Array<Processo>();
  usuarioLogado = new Usuario();

  constructor(
    private _location: Location,
    private _router: Router,
    private _processoService: ProcessoService,
    private _admComponent: AdmComponent
  ) { }

  ngOnInit() {
    this._processoService.recuperaTodosProcessosPendentesPorUsuario(this.usuarioLogado.id).subscribe(data => {
      this.lstProcessosPendentes = (data);
    });
  }

  redirecionaPagina(urlPage: string) {
    this._router.navigate([urlPage]);
  }

  voltarPagina() {
    this._location.back();
  }
}
