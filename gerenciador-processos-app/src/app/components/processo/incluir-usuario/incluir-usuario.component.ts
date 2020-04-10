import {Component, Input, OnInit} from '@angular/core';
import {Usuario} from '../../../model/usuario.model';
import {UsuarioService} from '../../usuario/usuario.service';
import {ActivatedRoute, Router} from '@angular/router';
import {FormControl, FormGroup} from '@angular/forms';
import {ProcessoService} from '../processo.service';
import {Processo} from '../../../model/processo.model';

@Component({
  selector: 'app-incluir-usuario',
  templateUrl: './incluir-usuario.component.html',
  styleUrls: ['./incluir-usuario.component.css']
})
export class IncluirUsuarioComponent implements OnInit {

  usuariosVinculados: Usuario[];
  @Input() processo: Processo;

  usuariosForm = new FormGroup({
    usuariosVinculados: new FormControl('')
  });

  constructor(private usuarioService: UsuarioService,
              private route: ActivatedRoute,
              private processoService: ProcessoService,
              private router: Router) {
    usuarioService.getUsuarios().subscribe(usuarios => this.usuariosVinculados = usuarios);
  }

  onSubmit() {
    this.usuariosForm.value.usuariosVinculados = this.buildUsuariosVinculados(this.usuariosForm.value.usuariosVinculados);
    this.addUsuarios(this.usuariosForm.value);
  }

  addUsuarios(usuarios: any): void {
    this.processoService.addUsuarios(this.processo.id, usuarios.usuariosVinculados)
      .subscribe(() => this.router.navigate(['/processos']));
  }

  getProcesso(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.processoService.getProcesso(id)
      .subscribe(processo => {
          this.processo = processo;
        }
      );
  }

  buildUsuariosVinculados(usuariosVinculados: string[]): string[] {
    const usuarios = [];
    usuariosVinculados.forEach(u => {
      usuarios.push(u.split(/[/ ]+/).pop());
    });
    return usuarios;
  }

  ngOnInit() {
    this.getProcesso();
  }

}
