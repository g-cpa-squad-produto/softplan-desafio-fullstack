import { Component, OnInit, ViewChild } from '@angular/core';
import { Processo } from '../processo.entity';
import { ProcessoService } from '../processo.service';
import { Router } from '@angular/router';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';

@Component({
  selector: 'app-processo-listagem',
  templateUrl: './processo-listagem.component.html',
  styleUrls: ['./processo-listagem.component.css']
})
export class ProcessoListagemComponent implements OnInit {

  dataSourceTable: MatTableDataSource<Processo>;
  columnsToDisplay: string[] = ['nome', 'numero', 'ano', 'cadastro', 'actions'];
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private processoService: ProcessoService,
              private router: Router) { }

  ngOnInit() {
    this.processoService.listarTodos().subscribe(list => {
      this.dataSourceTable = new MatTableDataSource(list);
      this.dataSourceTable.sort = this.sort;
      this.dataSourceTable.paginator = this.paginator;
      this.dataSourceTable.filterPredicate = (data, filter) => {
        return this.columnsToDisplay.some(f => {
          return f !== 'actions' && data[f].toLowerCase().indexOf(filter) !== -1;
        });
      };
    });
  }

  filterTable(nome: string) {
    this.dataSourceTable.filter = nome.trim().toLocaleLowerCase();
  }

  editar(id: string) {
    this.router.navigate(['./processo/editar', id]);
  }

  visualizar(id: string) {
    this.router.navigate(['./processo/visualizar', id]);
  }

  cadastrar() {
    this.router.navigate(['./processo/cadastro']);
  }
}
