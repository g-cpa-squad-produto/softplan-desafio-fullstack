import {Usuario} from '../../core/models/pessoa.model';
import {Component, OnInit} from '@angular/core';
import {UsuarioService} from '../../core/services/usuario.service';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-controle-usuario',
  templateUrl: './controle-usuario.component.html',
  styleUrls: ['./controle-usuario.component.css']
})
export class ControleUsuarioComponent implements OnInit {
  model: Usuario;
  usuarios: Usuario[];

  constructor(private usuarioService: UsuarioService,
              private toastr: ToastrService) {
  }

  ngOnInit() {
    this.model = new Usuario();
    this.buscarUsuarios();
  }

  cadastrar() {
    if (this.model.id) {
      console.log('Tem id');
      this.usuarioService.editar(this.model).subscribe(resp => {
        this.model = new Usuario();
        this.buscarUsuarios();
        this.toastr.success('Editado!', 'com Sucesso!');
      }, error => {
        this.toastr.error('Contate o Administrador!', 'Erro!');
      });
    } else {
      console.log('Não tem id');
      this.usuarioService.cadastrar(this.model).subscribe(resp => {
        this.toastr.success('Cadastrado!', 'com Sucesso!');
        this.buscarUsuarios();
        this.model = new Usuario();
      }, error => {
        this.toastr.error('Contate o Administrador!', 'Erro!');
      });
    }
  }

  private buscarUsuarios() {
    this.usuarioService.buscarUsuarios().subscribe(resp => {
      this.usuarios = resp.data;
      console.log(this.usuarios);
    }, error => {
      this.toastr.error('Contate o Administrador!', 'Erro!');
    });
  }

  editar(u: Usuario) {
    this.model = new Usuario();
    this.model = u;
  }
}

