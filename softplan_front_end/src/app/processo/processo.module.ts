import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';

import {ProcessoListComponent} from './list/processo-list.component';
import {ProcessoFormComponent} from './form/processo-form.component';
import {ProcessoDetailComponent} from './view/processo-detail.component';
import {ProcessoRoutes} from './processo.routing';

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        RouterModule.forChild(ProcessoRoutes)
    ],
    declarations: [
        ProcessoListComponent,
        ProcessoFormComponent,
        ProcessoDetailComponent
    ]
})

export class ProcessoModule {
}
