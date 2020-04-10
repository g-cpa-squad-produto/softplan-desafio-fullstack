import {Component, Input, OnInit} from '@angular/core';
import {Processo} from '../../../model/processo.model';
import {UsuarioService} from '../../usuario/usuario.service';
import {ActivatedRoute, Router} from '@angular/router';
import {ProcessoService} from '../processo.service';

@Component({
  selector: 'app-visualizar-processo',
  templateUrl: './visualizar-processo.component.html',
  styleUrls: ['./visualizar-processo.component.css']
})
export class VisualizarProcessoComponent implements OnInit {

  @Input() processo: Processo;

  constructor(private route: ActivatedRoute,
              private processoService: ProcessoService) {
  }

  ngOnInit() {
    this.getProcesso();
  }

  getProcesso(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.processoService.getProcesso(id)
      .subscribe(processo => {
          this.processo = processo;
        }
      );
  }

}
