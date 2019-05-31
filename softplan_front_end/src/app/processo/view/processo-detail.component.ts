import {Component} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {ProcessoDto} from "../../dto/processo.dto";
import {HttpClient} from "@angular/common/http";

@Component({
    selector: 'app-processo-view',
    templateUrl: './processo-detail.component.html',
    styleUrls: ['./processo-detail.component.css']
})
export class ProcessoDetailComponent {

    processo: ProcessoDto;

    constructor(private http: HttpClient,
                private route: ActivatedRoute) {
        this.processo = this.route.snapshot.params as ProcessoDto;

    }
}
