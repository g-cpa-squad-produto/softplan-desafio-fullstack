import { Component, OnInit } from '@angular/core';
import { UsuarioService } from 'src/app/service/usuario/usuario.service';
import { Usuario } from 'src/app/modelos/user';
import { Util } from 'src/app/util';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  providers: [UsuarioService]
})
export class HomeComponent implements OnInit {

  constructor(public usuarioService: UsuarioService) { }
  public usuarios: [Usuario];
  public user: Usuario = new Usuario();
  ngOnInit() {
    this.listarUsuarios();
  }

  cadastrarUsuario(usuario: Usuario) {
    if (this.usuarioValido(usuario)) {
      this.usuarioService.cadastrarUsuario(usuario).toPromise().then(
        data => {
          this.user = new Usuario();
          this.listarUsuarios();
          alert('Pronto')
        }
      ).catch(
        error => {
          console.log(error)
          alert('Parametros invalidos');
        }
      )
    } else {
      alert('Parametros invalidos');
    }
  }
  usuarioValido(usuario: Usuario) {
    if (usuario.nome == null) {
      return false;
    }
    if (usuario.login == null) {
      return false;
    }
    if (usuario.id == null) {
      if (usuario.senha == null) {
        return false;
      }
    }
    if (usuario.perfil == null) {
      return false;
    }
    return true;
  }

  deletarUsuario(id) {
    if (id == window.localStorage.getItem(Util.ID_DO_USUARIO)) {
      alert('Nao pode deletar a si mesmo')
      return;
    }
    if (confirm('Deseja deletar este usuario?')) {
      this.usuarioService.deletarUsuario(id).toPromise().then(
        data => {
          alert('Usuario deletado');
          this.listarUsuarios()
        }
      )
    }
  }

  listarUsuarios() {
    this.usuarioService.getAllUser().toPromise().then(
      data => {
        this.usuarios = data.data
        console.log(this.usuarios)
      }
    )
  }
}
