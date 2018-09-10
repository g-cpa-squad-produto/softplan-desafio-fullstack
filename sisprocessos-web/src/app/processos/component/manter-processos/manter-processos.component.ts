import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {Enum} from '../../../shared/model/enum';
import {IMyDate, IMyDateModel} from 'mydatepicker';
import {ActivatedRoute, Router} from '@angular/router';
import {Processo} from '../../model/processo';
import {ProcessosService} from '../../service/processos.service';

@Component({
  selector: 'manter-processos',
  templateUrl: './manter-processos.component.html',
  styleUrls: ['./manter-processos.component.css']
})
export class ManterProcessosComponent implements OnInit {

  readonly: boolean;
  tituloPagina: string;
  processo: Processo;
  status$: Observable<Enum[]>;
  dataSelecionada: IMyDate = {year: 0, month: 0, day: 0};

  constructor(private service: ProcessosService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit() {
    this.processo = this.route.snapshot.data.processo;
    this.readonly = this.route.snapshot.data.readonly || false;
    this.status$ = this.service.pesquisarStatus();

    this.prepararTela();
  }

  salvar() {
    this.service.cadastrar(this.processo).subscribe(
      () => this.router.navigate(['processos']),
      error => alert(error));
  }

  prepararTela() {
    if (this.processo) {
      this.tituloPagina = 'Visualizar Processos';
    } else {
      this.tituloPagina = 'Cadastrar Processos';
      this.processo = new Processo();
      this.processo.data = new Date();
    }

    // FIXME data está vindo como string
    // this.dataSelecionada.day = this.processo.data.getDate();
    // this.dataSelecionada.month = this.processo.data.getMonth() + 1;
    // this.dataSelecionada.year = this.processo.data.getFullYear();
  }

  onDateChanged(event: IMyDateModel) {
    this.dataSelecionada = event.date;
    this.processo.data = event.jsdate;
  }


}
