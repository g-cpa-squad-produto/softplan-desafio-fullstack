import {Component} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {UsuarioDto} from "../../dto/usuario.dto";

@Component({
    selector: 'app-usuario-view',
    templateUrl: './view.html',
    styleUrls: ['./view.css']
})
export class View {

    usuario: UsuarioDto;

    constructor(private route: ActivatedRoute) {
        this.usuario = this.route.snapshot.params as UsuarioDto;
    }
}
