import { Component, OnInit, TemplateRef } from '@angular/core';
import { Processo } from 'src/app/modelos/processo';
import { ProcessosService } from 'src/app/service/processos/processos.service';
import { Usuario } from 'src/app/modelos/user';
import { UsuarioService } from 'src/app/service/usuario/usuario.service';
import { BsModalRef } from 'ngx-bootstrap';
import { BsModalService } from 'ngx-bootstrap/modal';


@Component({
  selector: 'app-processos',
  templateUrl: './processos.component.html',
  styleUrls: ['./processos.component.css'],
  providers: [ProcessosService]
})
export class ProcessosComponent implements OnInit {

  public processo: Processo = new Processo();
  public processos: [Processo]
  private usuarios: [Usuario];
  private usuarioResponsaveis: [] = [];
  modalRef: BsModalRef;
  constructor(private modalService: BsModalService, private usuarioService: UsuarioService, public processoService: ProcessosService) { }
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
    this.processo.usuarios = this.usuarioResponsaveis;
    this.processoService.cadastrar(processo).toPromise().then(
      data => {
        this.listarTodosProcessos();
        this.processo = new Processo();
        this.usuarioResponsaveis = [];
        alert('Pronto');
      }
    )
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
