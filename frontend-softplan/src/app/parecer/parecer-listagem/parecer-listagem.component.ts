import { Component, OnInit, ViewChild } from '@angular/core';
import { ParecerService } from '../parecer.service';
import { Processo } from 'src/app/processo/processo.entity';
import { Router } from '@angular/router';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-parecer-listagem',
  templateUrl: './parecer-listagem.component.html',
  styleUrls: ['./parecer-listagem.component.css']
})
export class ParecerListagemComponent implements OnInit {

  processosSemParecer: Processo[] = new Array();
  dataSourceTable: MatTableDataSource<Processo>;
  columnsToDisplay: string[] = ['nome', 'numero', 'ano', 'cadastro', 'actions'];

  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private parecerService: ParecerService,
              private router: Router) { }

  ngOnInit() {
    this.parecerService.listProcessosSemParecer().subscribe(res => {
      this.dataSourceTable = new MatTableDataSource(res);
      this.dataSourceTable.sort = this.sort;
      this.dataSourceTable.paginator = this.paginator;
      this.dataSourceTable.filterPredicate = (data, filter) => {
        return this.columnsToDisplay.some(f => {
          return f !== 'actions' && data[f].toLowerCase().indexOf(filter) !== -1;
        });
      };
    });
  }

  adicionarParecer(processoId: string) {
    this.router.navigate(['./parecer/adicionar', processoId]);
  }

  filterTable(nome: string) {
    this.dataSourceTable.filter = nome.trim().toLocaleLowerCase();
  }
}
