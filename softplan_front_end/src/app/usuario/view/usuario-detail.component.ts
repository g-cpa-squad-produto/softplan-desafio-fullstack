import {Component} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {UsuarioDto} from "../../dto/usuario.dto";

@Component({
    selector: 'app-usuario-view',
    templateUrl: './usuario-detail.component.html',
    styleUrls: ['./usuario-detail.component.css']
})
export class UsuarioDetailComponent {

    usuario: UsuarioDto;

    constructor(private route: ActivatedRoute) {
        this.usuario = this.route.snapshot.params as UsuarioDto;
    }
}
