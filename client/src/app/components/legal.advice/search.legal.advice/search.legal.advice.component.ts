import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { LegalAdviceService } from '../../../services/legal.advice/legal.advice.service';
import { LegalAdvice } from '../../../services/legal.advice/legal.advice.model'

@Component({
  selector: 'app-search-legal-advice-component',
  templateUrl: './search.legal.advice.component.html',
  styleUrls: ['./search.legal.advice.component.scss']
})
export class SearchLegalAdviceComponent implements OnInit {

  legalAdvices: LegalAdvice[];

  // exibicao de mensagem de erro na tela
  isError: Boolean = false;
  error: string;

  // exibicao de loader na tela
  exibeProgress: Boolean = false;
  value: Number = 0;

  constructor(
    private legalAdviceService: LegalAdviceService,
    private router: Router
  ) { }

  routeFormLegalAdvice(id) {
    this.router.navigate([`/legalAdvice/form/${id}`]);
  }

  routeFormNewLegalAdvice() {
    this.router.navigate([`/legalAdvice/form`]);
  }

  back() {
    window.history.back();
  }

  ngOnInit() {
    this.legalAdviceService.getAllLegalAdvices()
      .subscribe(res => {
        console.log('>>>> get legal advices res=', res);
        this.legalAdvices = res;
        this.exibeProgress = false;
    },
    error => {
      console.log('error service get legal advices ==>', error);
      this.exibeProgress = false;
      this.isError = true;
      this.error = `Não há legal advices para ser exibido`;
    });
  }
}
