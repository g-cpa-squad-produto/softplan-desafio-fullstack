import {Component, OnInit, ViewChild} from '@angular/core';
import {RetornoConsultaApi} from '../../../shared/model/retorno-consulta-api.model';
import {DatatableComponent} from '@swimlane/ngx-datatable';
import {Router} from '@angular/router';
import {PageInfo} from '../../../shared/model/page-info';
import {ProcessoResumido} from '../../model/processo-resumido';
import {ProcessosService} from '../../service/processos.service';

@Component({
  selector: 'listar-processos',
  templateUrl: './listar-processos.component.html',
  styleUrls: ['./listar-processos.component.css']
})
export class ListarProcessosComponent implements OnInit {

  dados: RetornoConsultaApi<ProcessoResumido> = new RetornoConsultaApi<ProcessoResumido>();
  @ViewChild(DatatableComponent) dataTable: DatatableComponent;

  columns = [
    {title: 'Número', name: 'numero'},
    {title: 'Descrição', name: 'descricao'},
    {title: 'Data', name: 'data'},
    {title: 'Status', name: 'status'},
    {title: 'Ações', name: 'acoes'}
  ];

  constructor(private service: ProcessosService, private router: Router) {
  }

  ngOnInit() {
    this.pesquisar(0);
  }

  pesquisar(numPagina: number): void {
    this.service.filtrar<ProcessoResumido>(null, numPagina.toString(), '5')
      .subscribe(retorno => this.dados = retorno);
  }

  visualizar(processo: ProcessoResumido): void {
    this.router.navigate(['processos', processo.id]);
  }

  atribuirUsuariosParecer(processo: ProcessoResumido): void {
    this.router.navigate(['processos', 'atribuir-usuarios', processo.id]);
  }

  incluirParecer(processo) {
    this.router.navigate(['processos', 'incluir-parecer', processo.id]);
  }

  setPage(pageInfo: PageInfo) {
    this.pesquisar(pageInfo.offset);
  }
}
