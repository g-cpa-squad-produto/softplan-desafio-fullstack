import {Directive, ElementRef, Input, OnInit, Renderer2} from '@angular/core';
import {AuthService} from '../auth/auth.service';

@Directive({
  selector: '[permissao]'
})
export class PermissaoDirective implements OnInit {

  @Input() permissao: Array<string>;

  constructor(private el: ElementRef, private renderer: Renderer2, private authService: AuthService) {
  }

  ngOnInit(): void {
    const perfilUsuarioLogado = this.authService.getUsuarioAutenticado().perfil.codigo;
    if (this.permissao.indexOf(perfilUsuarioLogado) === -1) {
      this.renderer.setStyle(this.el.nativeElement, 'display', 'none');
    }
  }

}
