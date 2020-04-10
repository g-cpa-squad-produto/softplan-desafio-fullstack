import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {FormControl, FormGroup} from '@angular/forms';
import {ProcessoService} from '../processo.service';
import {Processo} from '../../../model/processo.model';
import {Parecer} from '../../../model/parecer.model';

@Component({
  selector: 'app-novo-parecer',
  templateUrl: './novo-parecer.component.html',
  styleUrls: ['./novo-parecer.component.css']
})
export class NovoParecerComponent implements OnInit {

  @Input() processo: Processo;

  parecerForm = new FormGroup({
    descricao: new FormControl('')
  });

  constructor(private route: ActivatedRoute, private processoService: ProcessoService, private router: Router) { }

  ngOnInit() {
    this.getProcesso();
  }
  onSubmit() {
    this.addParecer(this.parecerForm.value);
  }

  getProcesso(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.processoService.getProcesso(id)
      .subscribe(processo => {
          this.processo = processo;
        }
      );
  }

  addParecer(parecerForm: any): void {
    this.processoService.addParecer(this.processo.id, parecerForm)
      .subscribe(() => this.router.navigate(['/processos']));
  }

}
