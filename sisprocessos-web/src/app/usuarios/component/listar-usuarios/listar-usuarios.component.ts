import {Component, OnInit, ViewChild} from '@angular/core';
import {Router} from '@angular/router';
import {DatatableComponent} from '@swimlane/ngx-datatable';

import {UsuariosService} from '../../service/usuarios.service';
import {RetornoConsultaApi} from '../../../shared/model/retorno-consulta-api.model';
import {UsuarioResumido} from '../../model/usuario-resumido';
import {PageInfo} from '../../../shared/model/page-info';

@Component({
  selector: 'listar-usuarios',
  templateUrl: './listar-usuarios.component.html',
  styleUrls: ['./listar-usuarios.component.css']
})
export class ListarUsuariosComponent implements OnInit {

  dados: RetornoConsultaApi<UsuarioResumido> = new RetornoConsultaApi<UsuarioResumido>();
  @ViewChild(DatatableComponent) dataTable: DatatableComponent;

  columns = [
    {title: 'Nome', name: 'nome'},
    {title: 'CPF', name: 'cpf'},
    {title: 'Aniversário', name: 'aniversario'},
    {title: 'Perfil', name: 'perfil'},
    {title: 'Ações', name: 'acoes'}
  ];

  constructor(private service: UsuariosService, private router: Router) {
  }

  ngOnInit() {
    this.pesquisar(0);
  }

  pesquisar(numPagina: number): void {
    this.service.filtrar<UsuarioResumido>(null, numPagina.toString(), '5')
      .subscribe(retorno => this.dados = retorno);
  }

  visualizar(usuario: UsuarioResumido): void {
    this.router.navigate(['usuarios', usuario.id]);
  }

  editar(usuario: UsuarioResumido): void {
    this.router.navigate(['usuarios', 'editar', usuario.id]);
  }

  excluir(usuario: UsuarioResumido): void {
    this.service.deletar(usuario.id).subscribe(() => {
      this.pesquisar(this.dataTable.offset);
      alert('Usuário excluído!!!');
    }, error => {
      alert(error);
    });
  }

  toggleAtivar(usuario: UsuarioResumido): void {
    if (usuario.ativo) {
      this.inativar(usuario);
    } else {
      this.ativar(usuario);
    }

  }

  private inativar(usuario: UsuarioResumido) {
    this.service.inativarUsuario(usuario.id).subscribe(() => {
      this.pesquisar(this.dataTable.offset);
      alert('Usuário bloqueado!!!');
    }, error => {
      alert(error);
    });
  }

  private ativar(usuario: UsuarioResumido) {
    this.service.ativarUsuario(usuario.id).subscribe(() => {
      this.pesquisar(this.dataTable.offset);
      alert('Usuário desbloqueado!!!');
    }, error => {
      alert(error);
    });
  }

  setPage(pageInfo: PageInfo) {
    this.pesquisar(pageInfo.offset);
  }

}
