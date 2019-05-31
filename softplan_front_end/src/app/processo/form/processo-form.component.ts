import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';

import {ProcessoDto} from '../../dto/processo.dto';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
    selector: 'app-processo-form',
    templateUrl: './processo-form.component.html',
    styleUrls: ['./processo-form.component.css']
})
export class ProcessoFormComponent implements OnInit {

    processo: ProcessoDto;

    loginFinalizadores: Array<String>;

    constructor(private http: HttpClient, private router: Router, private route: ActivatedRoute) {
        if (this.route.snapshot.params.id) {
            this.processo = this.getRecord();
        } else {
            this.processo = ProcessoFormComponent.getBlankRecord();
        }
    }

    static getBlankRecord(): ProcessoDto {
        return {
            isNew: true,
            id: null,
            titulo: '',
            descricao: '',
            dataCriacao: null,
            createdBy: '',
            dataFinalizacao: null,
            finalizadores: [],
            finalizadoresEscolhidos: [],
        };
    }

    getRecord(): ProcessoDto {
        return {
            isNew: false,
            id: this.route.snapshot.params.id,
            titulo: this.route.snapshot.params.titulo,
            descricao: this.route.snapshot.params.descricao,
            dataCriacao: this.route.snapshot.params.dataCriacao,
            createdBy: this.route.snapshot.params.createdBy,
            dataFinalizacao: this.route.snapshot.params.dataFinalizacao,
            finalizadores: this.route.snapshot.params.finalizadores,
            finalizadoresEscolhidos: this.route.snapshot.params.finalizadoresEscolhidos
        };
    }

    ngOnInit() {
        this.http.get('http://localhost:8080/usuario/findAllNomeUsuarioFinalizadores').subscribe(
            resp => {
                this.loginFinalizadores = (resp as Array<String>);
            },
            err => {
                console.log('Error occured!', err);
            });
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
                alert('Processo cadastrado com sucesso!');
                this.router.navigate(['./processo-list']);
            },
            err => {
                alert('Erro ao cadastrar processo: ' + err.error.message);
                console.log('Error occured', err);
            }
        );
    }

    async update() {
        this.http.put(`http://localhost:8080/processo/${this.processo.id}`, this.processo).subscribe(
            res => {
                console.log(res);
                alert('Processo atualizado com sucesso!');
                this.router.navigate(['./processo-list']);
            },
            err => {
                alert('Erro ao atualizar processo: ' + err.error.message);
                console.log('Error occured', err);
            }
        );
    }
}
