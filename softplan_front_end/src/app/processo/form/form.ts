import {Component} from '@angular/core';
import {HttpClient} from '@angular/common/http';

import {ProcessoDto} from '../../dto/processo.dto';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
    selector: 'app-processo-form',
    templateUrl: './form.html',
    styleUrls: ['./form.css']
})
export class Form {

    processo: ProcessoDto;

    constructor(private http: HttpClient, private router: Router, private route: ActivatedRoute) {
        if (this.route.snapshot.params.id) {
            this.processo = this.getRecord();
        } else {
            this.processo = Form.getBlankRecord();
        }
    }

    getRecord(): ProcessoDto {
        return {
            id: this.route.snapshot.params.id,
            titulo: this.route.snapshot.params.titulo,
            descricao: this.route.snapshot.params.descricao,
            dataCriacao: this.route.snapshot.params.dataCriacao,
            createdBy: this.route.snapshot.params.createdBy,
            dataFinalizacao: this.route.snapshot.params.dataFinalizacao,
            responsavel: this.route.snapshot.params.responsavel
        };
    }

    static getBlankRecord(): ProcessoDto {
        return {
            id: null,
            titulo: '',
            descricao: '',
            dataCriacao: null,
            createdBy: '',
            dataFinalizacao: null,
            responsavel: ''
        };
    }

    save() {
        if (this.processo.id) {
            return this.update();
        } else {
            return this.create();
        }
    }

    async create() {
        this.http.post('http://localhost:8080/processo', this.processo).subscribe(
            res => {
                console.log(res);
                alert("Processo cadastrado com sucesso!");
                this.router.navigate(['./processo-list']);
            },
            err => {
                alert("Erro ao cadastrar processo: " + err.error.message);
                console.log("Error occured", err);
            }
        );
    }

    async update() {
        this.http.put(`http://localhost:8080/processo/${this.processo.id}`, this.processo).subscribe(
            res => {
                console.log(res);
                alert("Processo atualizado com sucesso!");
                this.router.navigate(['./processo-list']);
            },
            err => {
                alert("Erro ao atualizar processo: " + err.error.message);
                console.log("Error occured", err);
            }
        );
    }
}
