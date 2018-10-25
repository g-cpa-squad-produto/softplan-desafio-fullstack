import { Component, OnInit } from '@angular/core';
import { ResponseModel } from 'src/app/response/response.entity';
import { ProcessoService } from '../processo.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Processo } from '../processo.entity';

@Component({
  selector: 'app-processo-create',
  templateUrl: './processo-create.component.html',
  styleUrls: ['./processo-create.component.css']
})
export class ProcessoCreateComponent implements OnInit {

  processo: Processo = new Processo();
  titulo: String;
  private response: ResponseModel = new ResponseModel();
  constructor(private usuarioService: ProcessoService,
              private router: Router,
              private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.activatedRoute.params.subscribe(parameters => {
      if (parameters['id'] !== undefined) {
        this.titulo = 'Editar Processo';
        this.usuarioService.getProcessoById(parameters['id']).subscribe(res => this.processo = res);
      } else {
        this.titulo = 'Criar Processo';
      }
    });
  }

  voltar() {
    this.router.navigate(['./processo']);
  }

  salvar() {
    if (this.processo.id === undefined) {
      this.usuarioService.inserir(this.processo).subscribe(res => {
        this.response = <ResponseModel>res;
        if (this.response.id === 1) {
          alert(this.response.mensagem);
          this.voltar();
        } else {
          alert(this.response.mensagem);
        }
      }
      );
    } else {
      this.usuarioService.salvar(this.processo).subscribe(res => {
        this.response = <ResponseModel>res;
        if (this.response.id === 1) {
          alert(this.response.mensagem);
          this.voltar();
        } else {
          alert(this.response.mensagem);
        }
      });
    }
  }
}
