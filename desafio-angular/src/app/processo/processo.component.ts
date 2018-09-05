import { Processo } from './processo.model';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';


@Component({
  selector: 'app-processo',
  templateUrl: './processo.component.html',
  styleUrls: ['./processo.component.css']
})
export class ProcessoComponent implements OnInit {

  formProcesso: FormGroup;
  selecionado: Processo;

  constructor(private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.selecionado = new Processo();
    this.formProcesso = this.formBuilder.group({
      numero: [null, [Validators.compose([Validators.pattern('[A-Za-z]{1}[0-9]{2}[A-Za-z]{2}[A-Za-z0-9]{2}'), Validators.maxLength(7)])]],
      dados: [null, [Validators.required]],
      pendente: [null, [Validators.compose([Validators.required, Validators.minLength(1)])]],
      situacao: [null, [Validators.required]],
      prefixo: [null, [Validators.compose([Validators.pattern('[A-Za-z]{1}[0-9]{2}[A-Za-z]{2}[A-Za-z0-9]{2}'), Validators.maxLength(7)])]],
      numeroInicial: [null, [Validators.required, Validators.maxLength(9)]],
      quantidadeFiltro: [null, [Validators.required]],
      numeroNotaFiscal: [null, [Validators.compose([Validators.pattern('[A-Za-z0-9]{0,10}')])]]
    });
  }

  salvar() {

  }

}
