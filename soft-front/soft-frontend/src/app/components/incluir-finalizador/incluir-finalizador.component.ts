import {Component, OnInit} from '@angular/core';
import {Processo} from '../../core/models/processo.model';
import {Usuario} from '../../core/models/pessoa.model';
import {UsuarioService} from '../../core/services/usuario.service';
import {ProcessoService} from '../../core/services/processo.service';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-incluir-finalizador',
  templateUrl: './incluir-finalizador.component.html',
  styleUrls: ['./incluir-finalizador.component.css']
})
export class IncluirFinalizadorComponent implements OnInit {
  usuarios: Usuario[];
  usuario: Usuario;
  finalizadores: Usuario[];
  finalizador: Usuario;
  processos: Processo[];

  constructor(private usuarioService: UsuarioService,
              private processoService: ProcessoService,
              private toastr: ToastrService) {
  }

  ngOnInit() {
    this.usuario = new Usuario();
    this.usuarioService.buscarUsuarios().subscribe(resp => {
      this.usuarios = resp.data.filter(usu => usu.tipoUsuario === 'TRIADOR');
    }, error => {
      this.toastr.error('Contate o Administrador!', 'Erro!');
    });
  }

  verificaUsuario() {
    this.finalizador = new Usuario();
    this.usuarioService.buscarUsuarios().subscribe(resp => {
      this.finalizadores = resp.data.filter(usu => usu.tipoUsuario === 'FINALIZADOR');
    }, error => {
      this.toastr.error('Contate o Administrador!', 'Erro!');
    });
  }

  buscaProcessos() {
    this.processoService.buscarTodosPendentes().subscribe(resp => {
      this.processos = resp.data;
    }, error => {
      this.toastr.error('Contate o Administrador!', 'Erro!');
    });
  }

  adicionarFinalizador(processo: Processo) {
    processo.usuarios.push(this.finalizador);
    this.processoService.atualizar(processo).subscribe(resp => {
      if (resp.cod === 200) {
        this.toastr.success('Finalizador: ' + this.finalizador.nome + 'Adicionado ao Processo: ' + processo.id);
      } else {
        this.toastr.success('Não foi possível adicionar Finalizador');
      }
    }, error => {
      this.toastr.error('Contate o Administrador!', 'Erro!');
    });

  }
}
