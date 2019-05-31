import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {UsuarioDto} from "../../dto/usuario.dto";

@Component({
    selector: 'app-usuario-list',
    templateUrl: './list.html',
    styleUrls: ['./list.css']
})
export class List implements OnInit {

    usuarios: Array<UsuarioDto>;
    filtro: any;

    constructor(private http: HttpClient, private router: Router) {
    }

    ngOnInit() {
        this.http.get('http://localhost:8080/usuario').subscribe(
            resp => {
                this.usuarios = (resp as Array<UsuarioDto>);
            },
            err => {
                console.log("Error occured!", err);
            })
    }

    delete(id) {
        console.log(JSON.stringify(id));
        this.http.delete('http://localhost:8080/usuario/' + id).subscribe(
            res => {
                console.log(res);
                alert("Usuario deletado com sucesso!");
                location.reload();
            },
            err => {
                console.log("Error occured!", err);
            }
        );
    }

    edit(usuario) {
        this.router.navigate(['./usuario-form', usuario]);
    }

    view(usuario) {
        this.router.navigate(['./usuario-view', usuario]);
    }

    findAllBy(params) {
        console.log(JSON.stringify(params));
        this.http.get('http://localhost:8080/usuario/findAllBy', {params: {filtro: params}}).subscribe(
            res => {
                this.usuarios = res as Array<UsuarioDto>;
            },
            err => {
                console.log("Error occured!", err);
            }
        );
    }
}
