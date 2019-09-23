import {Component, OnInit} from '@angular/core';
import {Usuario} from '../../../core/models/pessoa.model';
import {UsuarioService} from '../../../core/services/usuario.service';
import {Processo} from '../../../core/models/processo.model';
import {ProcessoService} from '../../../core/services/processo.service';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-processos',
  templateUrl: './processos.component.html',
  styleUrls: ['./processos.component.css']
})
export class ProcessosComponent implements OnInit {
  usuarios: Usuario[];
  usuario: Usuario;
  processos: Processo[];

  constructor(private usuarioService: UsuarioService,
              private processoService: ProcessoService,
              private toastr: ToastrService) {
  }

  ngOnInit() {
    this.usuario = new Usuario();
    this.usuarioService.buscarUsuarios().subscribe(resp => {
      this.usuarios = resp.data;
    });
  }

  verificaUsuario() {
    console.log(this.usuario);
    this.processos = [];
    if (this.usuario.tipoUsuario === 'TRIADOR') {
      this.processoService.buscarTodosTriador(this.usuario.id).subscribe(resp => {
        this.processos = resp.data;
      }, error => {
        this.toastr.error('Contate o Administrador!', 'Erro!');
      });
    } else if (this.usuario.tipoUsuario === 'FINALIZADOR') {
      this.processoService.buscarTodosFinalizador(this.usuario.id).subscribe(resp => {
        this.processos = resp.data;
      }, error => {
        this.toastr.error('Contate o Administrador!', 'Erro!');
      });
    }
  }
}
