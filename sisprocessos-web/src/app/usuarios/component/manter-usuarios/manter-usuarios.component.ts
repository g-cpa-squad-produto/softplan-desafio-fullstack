import {Component, OnInit} from '@angular/core';
import {Usuario} from '../../model/usuario';
import {Observable, of} from 'rxjs';
import {Enum} from '../../../shared/model/enum';
import {UsuariosService} from '../../service/usuarios.service';
import {ActivatedRoute, Router} from '@angular/router';
import {IMyDate, IMyDateModel} from 'mydatepicker';

@Component({
  selector: 'manter-usuarios',
  templateUrl: './manter-usuarios.component.html',
  styleUrls: ['./manter-usuarios.component.css']
})
export class ManterUsuariosComponent implements OnInit {

  readonly: boolean;
  tituloPagina: string;
  usuario: Usuario;
  status$: Observable<any[]>;
  perfis$: Observable<Enum[]>;
  dataSelecionada: IMyDate = {year: 0, month: 0, day: 0};

  constructor(private service: UsuariosService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit() {
    this.perfis$ = this.service.pesquisarPerfis();
    this.usuario = this.route.snapshot.data.usuario;
    this.readonly = this.route.snapshot.data.readonly || false;
    this.status$ = of([
      {label: 'Ativo', valor: true},
      {label: 'Inativo', valor: false}
    ]);

    this.prepararTela();
  }

  salvar() {
    if (this.isCadastro()) {
      this.service.cadastrar(this.usuario).subscribe(
        () => this.router.navigate(['usuarios']),
        error => alert(error));
    } else {
      this.service.editar(this.usuario).subscribe(
        () => this.router.navigate(['usuarios']),
        error => alert(error));
    }
  }

  prepararTela() {
    if (this.usuario) {
      this.tituloPagina = this.readonly ? 'Visualizar Usuário' : 'Editar Usuário';
    } else {
      this.tituloPagina = 'Cadastrar Usuário';
      this.usuario = new Usuario();
      this.usuario.aniversario = new Date();
    }

    // FIXME data está vindo como string
    // this.dataSelecionada.day = this.usuario.aniversario.getDate();
    // this.dataSelecionada.month = this.usuario.aniversario.getMonth() + 1;
    // this.dataSelecionada.year = this.usuario.aniversario.getFullYear();
  }

  private isCadastro(): boolean {
    return this.usuario.id == null;
  }

  onDateChanged(event: IMyDateModel) {
    this.dataSelecionada = event.date;
    this.usuario.aniversario = event.jsdate;
  }
}
