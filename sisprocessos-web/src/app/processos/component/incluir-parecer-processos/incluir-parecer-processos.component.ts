import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Processo} from '../../model/processo';
import {ProcessosService} from '../../service/processos.service';

@Component({
  selector: 'incluir-parecer-processos',
  templateUrl: './incluir-parecer-processos.component.html',
  styleUrls: ['./incluir-parecer-processos.component.css']
})
export class IncluirParecerProcessosComponent implements OnInit {

  processo: Processo;

  constructor(private service: ProcessosService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit() {
    this.processo = this.route.snapshot.data.processo;
  }

  salvar() {
    this.service.incluirParecer(this.processo).subscribe(
      () => this.router.navigate(['processos']),
      error => {
        console.log(error);
        alert(error);
      });
  }

}
