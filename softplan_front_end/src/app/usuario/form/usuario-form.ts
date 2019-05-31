import {Component} from '@angular/core';
import {HttpClient} from '@angular/common/http';

import {UsuarioDto} from '../../dto/usuario.dto';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
    selector: 'app-usuario-form',
    templateUrl: './form.html',
    styleUrls: ['./form.css']
})
export class UsuarioForm {

    usuario: UsuarioDto;

    constructor(private http: HttpClient, private router: Router, private route: ActivatedRoute) {
        if (this.route.snapshot.params.id) {
            this.usuario = this.getRecord();
        } else {
            this.usuario = UsuarioForm.getBlankRecord();
        }
    }

    getRecord(): UsuarioDto {
        return {
            id: this.route.snapshot.params.id,
            dataCriacao: this.route.snapshot.params.dataCriacao,
            dataModificacao: this.route.snapshot.params.dataModificacao,
            login: this.route.snapshot.params.login,
            senha: this.route.snapshot.params.senha,
            nomeCompleto: this.route.snapshot.params.nomeCompleto,
            email: this.route.snapshot.params.email,
            permissao: this.route.snapshot.params.permissao,
            createdBy: this.route.snapshot.params.createdBy,
        };
    }

    static getBlankRecord(): UsuarioDto {
        return {
            id: null,
            dataCriacao: null,
            dataModificacao: null,
            login: '',
            senha: '',
            nomeCompleto: '',
            email: '',
            permissao: null,
            createdBy: ''
        };
    }

    save() {
        if (this.usuario.id) {
            return this.update()
        } else {
            return this.create();
        }
    }

    async create() {
        this.http.post('http://localhost:8080/usuario', this.usuario).subscribe(
            res => {
                console.log(res);
                alert("Usuario cadastrado com sucesso!");
                this.router.navigate(['./usuario-list']);
            },
            err => {
                alert("Erro ao cadastrar usuario: " + err.error.message);
                console.log("Error occured", err);
            }
        );
    }

    async update() {
        this.http.put(`http://localhost:8080/usuario/${this.usuario.id}`, this.usuario).subscribe(
            res => {
                console.log(res);
                alert("Usuario atualizado com sucesso!");
                this.router.navigate(['./usuario-list']);
            },
            err => {
                alert("Erro ao atualizar usuario: " + err.error.message);
                console.log("Error occured", err);
            }
        );
    }
}
