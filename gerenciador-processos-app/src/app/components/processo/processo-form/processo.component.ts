import {Component, OnInit, Output} from '@angular/core';
import {Processo} from '../../../model/processo.model';
import {FormControl, FormGroup} from '@angular/forms';
import {ProcessoService} from '../processo.service';
import {Router} from '@angular/router';
import {Usuario} from '../../../model/usuario.model';
import {UsuarioService} from '../../usuario/usuario.service';

@Component({
  selector: 'app-processo',
  templateUrl: './processo.component.html',
  styleUrls: ['./processo.component.css']
})
export class ProcessoComponent implements OnInit {

  @Output() processo: Processo;

  usuariosVinculados: Usuario[];

  processoForm = new FormGroup({
    descricao: new FormControl(''),
    usuariosVinculados: new FormControl('')
  });

  constructor(private processoService: ProcessoService,
              private usuarioService: UsuarioService,
              private router: Router) {
    usuarioService.getUsuarios().subscribe(usuarios => this.usuariosVinculados = usuarios);
  }

  onSubmit() {
    this.processoForm.value.usuariosVinculados = this.buildUsuariosVinculados(this.processoForm.value.usuariosVinculados);
    this.addProcesso(this.processoForm.value);
  }

  addProcesso(processo: any): void {
    this.processoService.addProcesso(processo)
      .subscribe(() => this.router.navigate(['/processos']));
  }

  buildUsuariosVinculados(usuariosVinculados: string[]): string[] {
    const usuarios = [];
    usuariosVinculados.forEach(u => {
      usuarios.push(u.split(/[/ ]+/).pop());
    });
    return usuarios;
  }

  ngOnInit(): void {
  }

}
