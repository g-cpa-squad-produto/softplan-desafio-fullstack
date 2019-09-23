import {Component, OnInit} from '@angular/core';
import {Processo} from '../../core/models/processo.model';
import {ProcessoService} from '../../core/services/processo.service';
import {ToastrService} from 'ngx-toastr';
import {Router} from '@angular/router';
import {Usuario} from '../../core/models/pessoa.model';
import {UsuarioService} from '../../core/services/usuario.service';

@Component({
  selector: 'app-processo',
  templateUrl: './processo.component.html',
  styleUrls: ['./processo.component.css']
})
export class ProcessoComponent implements OnInit {
  model: Processo;
  usuarios: Usuario[];
  usuario: Usuario;

  constructor(private processoService: ProcessoService,
              private toastr: ToastrService,
              private usuarioService: UsuarioService,
              private router: Router) {

  }

  ngOnInit() {
    this.model = new Processo();
    this.usuario = new Usuario();
    this.usuarioService.buscarUsuarios().subscribe(resp => {
      this.usuarios = resp.data.filter(usu => usu.tipoUsuario === 'TRIADOR');
    });
  }

  cadastrar() {
    this.model.dataCadastro = formatDate(new Date());
    this.model.situacao = 'PENDENTE';
    console.log(JSON.stringify(this.model));
    this.processoService.cadastrar(this.model).subscribe(resp => {
      this.model = new Processo();
      this.toastr.success('Processo Inserido!', 'Sucesso!');
      this.router.navigate(['/processos']);
    }, error => {
      this.toastr.error('Contate o Administrador!', 'Erro!');
    });

    function _formatDatetime(date: Date, format: string) {
      const _padStart = (value: number): string => value.toString().padStart(2, '0');
      return format
        .replace(/yyyy/g, _padStart(date.getFullYear()))
        .replace(/dd/g, _padStart(date.getDate()))
        .replace(/mm/g, _padStart(date.getMonth() + 1));
    }

    function isValidDate(d: Date): boolean {
      return !isNaN(d.getTime());
    }

    function formatDate(date: any): string {
      const datetime = new Date(date);
      return isValidDate(datetime) ? _formatDatetime(datetime, 'yyyy-mm-dd') : '';
    }
  }
}
