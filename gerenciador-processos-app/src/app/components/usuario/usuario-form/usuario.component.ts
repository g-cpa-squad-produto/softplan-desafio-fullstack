import {Component, OnInit, Output} from '@angular/core';
import {Router} from '@angular/router';
import {UsuarioService} from '../usuario.service';
import {FormControl, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-usuario',
  templateUrl: './usuario.component.html',
  styleUrls: ['./usuario.component.css']
})
export class UsuarioComponent implements OnInit {

  tiposUsuario: any = ['ADMINISTRADOR', 'TRIADOS', 'FINALIZADOR'];

  constructor(private usuarioService: UsuarioService,
              private router: Router) {
  }

  usuarioForm = new FormGroup({
    nome: new FormControl(''),
    cpf: new FormControl(''),
    email: new FormControl(''),
    senha: new FormControl(''),
    tipoUsuario: new FormControl('')
  });

  onSubmit() {
    this.addUsuario(this.usuarioForm.value);
  }

  ngOnInit(): void {
  }

  addUsuario(usuario: any): void {
    this.usuarioService.addUsuario(usuario)
      .subscribe(() => this.router.navigate(['/usuarios']));
  }

}
