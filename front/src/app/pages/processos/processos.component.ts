import { Component, OnInit, TemplateRef } from '@angular/core';
import { Processo } from 'src/app/modelos/processo';
import { ProcessosService } from 'src/app/service/processos/processos.service';
import { Usuario } from 'src/app/modelos/user';
import { UsuarioService } from 'src/app/service/usuario/usuario.service';
import { BsModalRef } from 'ngx-bootstrap';
import { BsModalService } from 'ngx-bootstrap/modal';
import { ParecerService } from 'src/app/service/parecer/parecer.service';
import { Parecer } from 'src/app/modelos/parecer';


@Component({
  selector: 'app-processos',
  templateUrl: './processos.component.html',
  styleUrls: ['./processos.component.css'],
  providers: [ProcessosService, ParecerService]
})
export class ProcessosComponent implements OnInit {

  public processo: Processo = new Processo();
  public processos: [Processo]
  public usuarios: [Usuario];
  public usuarioResponsaveis: [] = [];
  public pareceres: [Parecer];
  public modalRef: BsModalRef;

  constructor(public modalService: BsModalService, public usuarioService: UsuarioService, public processoService: ProcessosService, public parecerService: ParecerService, ) { }
  ngOnInit() {
    this.listarTodosProcessos();
    this.listarUsuarios();
  }
  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template, { backdrop: 'static', keyboard: false });
  }

  atualizar(processo) {
    this.processo = processo;
  }

  cadastrarProcesso(processo: Processo) {
    if (this.processo.usuarios.length == 0) {
      this.processo.usuarios = this.usuarioResponsaveis;
    }
    if (this.processo.usuarios.length == 0) {
      alert('Escolha pelo menos um usuário')
      return;
    }
    this.processoService.cadastrar(processo).toPromise().then(
      data => {
        this.listarTodosProcessos();
        this.processo = new Processo();
        this.usuarioResponsaveis = [];
        var itens = document.querySelectorAll('input[type=checkbox]');
        this.limparCheckBox(itens);
        alert('Pronto');
      }
    )
  }
  limparCheckBox(itens) {
    for (var i = 0; i < itens.length; i++) {
      itens[i].checked = false;
    }
  }

  finalizarProcesso() {
    var totalDeParecer: number = this.pareceres.length;
    var totalDeUsuario: number = this.processo.usuarios.length;
    if (totalDeUsuario === totalDeParecer) {
      this.processo.finalizado = true;
      this.cadastrarProcesso(this.processo);
      this.processo = new Processo();
    } else {
      alert('Alguns usuarios não inseriram parecer')
      this.processo = new Processo();
    }
  }

  inserirUsuario(usuario) {
    this.usuarioResponsaveis = this.contemElemento(this.usuarioResponsaveis, usuario);
    console.log(this.usuarioResponsaveis)
  }
  deletarProcesso(id: number) {
    if (confirm('Deseja realmente excluir?')) {
      this.processoService.deletar(id).toPromise().then(
        data => {
          this.listarTodosProcessos();
        }
      )
    }
  }
  listarParecerDoProcesso(processo: Processo) {
    this.processo = processo;
    this.parecerService.listarParecerDoProcesso(processo.id).toPromise().then(
      data => {
        this.pareceres = data.data
      }
    )
  }
  listarTodosProcessos() {
    this.processoService.listarTodosProcessos().toPromise().then(
      data => {
        this.processos = data.data;
      }
    )
  }
  listarUsuarios() {
    this.usuarioService.getAllUser().toPromise().then(
      data => {
        this.usuarios = data.data
        console.log(this.usuarios)
      }
    )
  }
  contemElemento(array, valor) {
    var contem;
    for (var i = 0; i < array.length; i++) {
      if (array[i] === valor) {
        delete array[i];
        contem = true;
        array = array.filter(Boolean);
        break;
      }
    }
    if (!contem) {
      array.push(valor)
    }
    return array;
  }
}
