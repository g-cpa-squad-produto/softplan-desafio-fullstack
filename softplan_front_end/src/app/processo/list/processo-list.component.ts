import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {ProcessoDto} from '../../dto/processo.dto';

@Component({
    selector: 'app-processo-list',
    templateUrl: './processo-list.component.html',
    styleUrls: ['./processo-list.component.css']
})
export class ProcessoListComponent implements OnInit {

    processos: Array<ProcessoDto>;
    descricaoFilter: any;

    constructor(private http: HttpClient,
                private router: Router) {
    }

    ngOnInit() {
        this.http.get('http://localhost:8080/processo').subscribe(
            resp => {
                this.processos = (resp as Array<ProcessoDto>);
            },
            err => {
                console.log('Error occured!', err);
            });
    }

    delete(id) {
        console.log(JSON.stringify(id));
        this.http.delete(`http://localhost:8080/processo/${id}`).subscribe(
            res => {
                console.log(res);
                location.reload();
            },
            err => {
                console.log('Error occured!', err);
            }
        );
    }

    edit(processo) {
        this.router.navigate(['./processo-form', processo]);
    }

    view(processo) {
        this.router.navigate(['./processo-view', processo]);
    }

    findAllBy(descricao) {
        console.log(JSON.stringify(descricao));
        this.http.get(`http://localhost:8080/processo/${descricao}`).subscribe(
            res => {
                this.processos = res as Array<ProcessoDto>;
            },
            err => {
                console.log('Error occured!', err);
            }
        );
    }
}
