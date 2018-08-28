import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { ProcessService } from '../../../services/process/process.service';
import { Process } from '../../../services/process/process.model'

@Component({
  selector: 'app-search-process-component',
  templateUrl: './search.process.component.html',
  styleUrls: ['./search.process.component.scss']
})
export class SearchProcessComponent implements OnInit {

  processes: Process[];

  // exibicao de mensagem de erro na tela
  isError: Boolean = false;
  error: string;

  // exibicao de loader na tela
  exibeProgress: Boolean = false;
  value: Number = 0;

  constructor(
    private processService: ProcessService,
    private router: Router
  ) { }

  routeFormProcess(id) {
    this.router.navigate([`/process/form/${id}`]);
  }

  routeFormNewProcess() {
    this.router.navigate([`/process/form`]);
  }

  back() {
    window.history.back();
  }

  ngOnInit() {
    this.processService.getAllProcesses()
      .subscribe(res => {
        console.log('>>>> get processes res=', res);
        this.processes = res;
        this.exibeProgress = false;
        // this.router.navigate(['/users'])
    },
    error => {
      console.log('error service get processes ==>', error);
      this.exibeProgress = false;
      this.isError = true;
      this.error = `Não há processos para ser exibido`;
    });

  }
}
