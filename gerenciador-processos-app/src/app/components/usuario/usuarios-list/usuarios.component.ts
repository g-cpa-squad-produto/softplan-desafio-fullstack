import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Processo} from '../../../model/processo.model';
import {UsuarioService} from '../usuario.service';
import {Usuario} from '../../../model/usuario.model';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {ModalComponent} from '../../../modal/modal.component';

@Component({
  selector: 'app-usuarios',
  templateUrl: './usuarios.component.html',
  styleUrls: ['./usuarios.component.css']
})
export class UsuariosComponent implements OnInit {

  @Output() setUsuario = new EventEmitter<Usuario>();
  @Input() usuario: Usuario;
  usuarios: Usuario[];
  p = 1;

  constructor(private usuarioService: UsuarioService,
              private modalService: NgbModal) {
    usuarioService.getUsuarios().subscribe(usuario => this.usuarios = usuario);
  }

  ngOnInit(): void {
  }

  open(usuario: Usuario) {
    const modalRef = this.modalService.open(ModalComponent);
    modalRef.componentInstance.title = 'Remover';
    modalRef.componentInstance.message = 'Tem certeza que deseja remover o herói?';
    modalRef.componentInstance.button1 = 'Sim';
    modalRef.componentInstance.button2 = 'Não';
    this.emitSetUsuario(usuario);
  }

  emitSetUsuario(usuario: Usuario) {
    this.setUsuario.emit(usuario);
  }

}
