import { AdmComponent } from './../../../adm/adm.component';
import { ProcessoService } from './../../../servicos/processo.service';
import { Router } from '@angular/router';
import { Location } from '@angular/common';
import { Processo } from './../../../entidades/processo';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-list-proc',
  templateUrl: './list-proc.component.html',
  styleUrls: ['./list-proc.component.css']
})
export class ListProcComponent implements OnInit {

  lstProcessos = Array<Processo>();

  constructor(
    private _location: Location,
    private _router: Router,
    private _processoService: ProcessoService,
    private _admComponent: AdmComponent
  ) { }

  ngOnInit() {
    this._processoService.recuperaTodosProcessos().subscribe(data => {
      this.lstProcessos = (data);
    });
  }

  montaUsu(processo: Processo) {
    let nomes =  '';
    if (processo.lstUsuParecer !== undefined) {
      for (let index = 0; index < processo.lstUsuParecer.length; index++) {
        if (index !== 0 ) {
          nomes += ', ';
        }
        nomes += processo.lstUsuParecer[index].nome;
      }
    }
    return nomes;
  }

  redirecionaPagina(urlPage: string) {
    this._router.navigate([urlPage]);
  }

  voltarPagina() {
    this._location.back();
  }

}
