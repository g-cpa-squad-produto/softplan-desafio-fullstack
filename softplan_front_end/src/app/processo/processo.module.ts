import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';

import {List} from './list/list';
import {Form} from './form/form';
import {View} from './view/view';
import {ProcessoRoutes} from './processo.routing';

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        RouterModule.forChild(ProcessoRoutes)
    ],
    declarations: [
        List,
        Form,
        View
    ]
})

export class ProcessoModule {
}
