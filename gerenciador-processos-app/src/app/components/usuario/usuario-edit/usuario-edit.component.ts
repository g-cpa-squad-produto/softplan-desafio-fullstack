import {Component, Input, OnInit} from '@angular/core';
import {UsuarioService} from '../usuario.service';
import {ActivatedRoute, Router} from '@angular/router';
import {FormControl, FormGroup} from '@angular/forms';
import {ProcessoService} from '../../processo/processo.service';
import {Processo} from '../../../model/processo.model';
import {Usuario} from '../../../model/usuario.model';

@Component({
  selector: 'app-usuario-edit',
  templateUrl: './usuario-edit.component.html',
  styleUrls: ['./usuario-edit.component.css']
})
export class UsuarioEditComponent implements OnInit {

  @Input() usuario: Usuario;

  tiposUsuario: any = ['ADMINISTRADOR', 'TRIADOS', 'FINALIZADOR'];

  constructor(private usuarioService: UsuarioService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  usuarioForm = new FormGroup({
    id: new FormControl(''),
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
    this.getUsuario();
  }

  getUsuario(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.usuarioService.getUsuario(id)
      .subscribe(usuario => {
          this.usuario = usuario;
          this.usuarioForm.setValue({
            id: this.usuario.id,
            nome: this.usuario.nome,
            cpf: this.usuario.cpf,
            email: this.usuario.email,
            senha: this.usuario.senha,
            tipoUsuario: this.usuario.tipoUsuario,
          });
        }
      );
  }

  addUsuario(usuario: any): void {
    this.usuarioService.addUsuario(usuario)
      .subscribe(() => this.router.navigate(['/usuarios']));
  }

}
