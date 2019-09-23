import {Component, OnInit} from '@angular/core';
import {Processo} from '../../core/models/processo.model';
import {ProcessoService} from '../../core/services/processo.service';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-adicionar-parecer',
  templateUrl: './adicionar-parecer.component.html',
  styleUrls: ['./adicionar-parecer.component.css']
})
export class AdicionarParecerComponent implements OnInit {
  processos: Processo[];
  model: Processo;

  constructor(private processoService: ProcessoService,
              private toastr: ToastrService) {
  }

  ngOnInit() {
    this.processoService.buscarTodosPendentes().subscribe(resp => {
      console.log(resp);
      this.processos = resp.data;
    }, error => {
      this.toastr.error('Contate o Administrador!', 'Erro!');
    });
  }

  adicionarParecer(pro: Processo) {
    this.model = new Processo();
    this.model = pro;
  }

  concluir() {
    this.model.situacao = 'FINALIZADO';
    this.model.dataParecer = formatDate(new Date());
    this.processoService.atualizar(this.model).subscribe(resp => {
      if (resp.cod === 200) {
        this.processoService.buscarTodosPendentes().subscribe(resp => {
          console.log(resp);
          this.processos = resp.data;
        }, error => {
          this.toastr.error('Contate o Administrador!', 'Erro!');
        });
        this.toastr.success('Adicionado parecer com sucesso ao processo: ' + this.model.id);
      } else {
        this.toastr.success('Erro ao tentar adicionar o parecer!');
      }
    }, error => {
      this.toastr.error('Contate o Administrador!', 'Erro!');
    });

    function _formatDatetime(date: Date, format: string) {
      const _padStart = (value: number): string => value.toString().padStart(2, '0');
      return format
        .replace(/yyyy/g, _padStart(date.getFullYear()))
        .replace(/dd/g, _padStart(date.getDate()))
        .replace(/mm/g, _padStart(date.getMonth() + 1));
    }

    function isValidDate(d: Date): boolean {
      return !isNaN(d.getTime());
    }

    function formatDate(date: any): string {
      const datetime = new Date(date);
      return isValidDate(datetime) ? _formatDatetime(datetime, 'yyyy-mm-dd') : '';
    }
  }

}
