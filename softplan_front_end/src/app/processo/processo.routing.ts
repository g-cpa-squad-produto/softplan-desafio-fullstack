import {Routes} from '@angular/router';
import {ProcessoFormComponent} from './form/processo-form.component';
import {ProcessoListComponent} from './list/processo-list.component';
import {ProcessoDetailComponent} from './view/processo-detail.component';

export const ProcessoRoutes: Routes = [{
    path: '',
    redirectTo: 'processo-list',
    pathMatch: 'full',
}, {
    path: '',
    children: [
        {
            path: 'processo-list',
            component: ProcessoListComponent
        },
        {
            path: 'processo-form',
            component: ProcessoFormComponent
        },
        {
            path: 'processo-form/:codigo',
            component: ProcessoFormComponent
        },
        {
            path: 'processo-view',
            component: ProcessoDetailComponent
        },
        {
            path: 'processo-view/:codigo',
            component: ProcessoDetailComponent
        }]
}];
