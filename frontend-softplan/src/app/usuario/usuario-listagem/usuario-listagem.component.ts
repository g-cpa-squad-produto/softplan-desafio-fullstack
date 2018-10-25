import { Component, OnInit } from '@angular/core';
import { UsuarioService } from '../usuario.service';
import { Router } from '@angular/router';
import { Usuario } from '../usuario.entity';
import { ResponseModel } from 'src/app/response/response.entity';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-usuario-listagem',
  templateUrl: './usuario-listagem.component.html',
  styleUrls: ['./usuario-listagem.component.css']
})
export class UsuarioListagemComponent implements OnInit {

  private usuarios: Usuario[] = new Array<Usuario>();
  private index = 0;
  columnsToDisplay = ['nome', 'email'];
  private response: ResponseModel = new ResponseModel();
  tableDataSource: MatTableDataSource<Usuario>;

  constructor(private usuarioService: UsuarioService,
              private router: Router) { }

  ngOnInit() {
    this.usuarioService.listarTodos().subscribe(res => this.usuarios = res);
    this.tableDataSource = new MatTableDataSource(this.usuarios);
    alert(this.tableDataSource);
  }

  filterTable(nome: string) {
    this.tableDataSource.filter = nome.trim().toLocaleLowerCase();
  }

  cadastrar() {
    this.router.navigate(['./usuario/cadastro']);
  }

  editar(id: string) {
    this.router.navigate(['./usuario/editar', id]);
  }

  visualizar(id: string) {
    this.router.navigate(['./usuario/visualizar', id]);
  }

  excluir(id: string, indexOf: number) {
    this.index = indexOf;
    if (confirm('Deseja realmente excluir esse registro?') === true) {
      this.usuarioService.apagar(id).subscribe(res => {
        this.response = <ResponseModel>res;
        if (this.response.id === 1) {
          alert(this.response.mensagem);
        } else {
          alert(this.response.mensagem);
        }
      });
      this.usuarios.splice(this.index, 1);
    }
  }
}
