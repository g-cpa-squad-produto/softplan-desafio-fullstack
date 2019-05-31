import {Routes} from '@angular/router';
import {Form} from './form/form';
import {List} from './list/list';
import {View} from './view/view';

export const ProcessoRoutes: Routes = [{
    path: '',
    redirectTo: 'processo-list',
    pathMatch: 'full',
}, {
    path: '',
    children: [
        {
            path: 'processo-list',
            component: List
        },
        {
            path: 'processo-form',
            component: Form
        },
        {
            path: 'processo-form/:codigo',
            component: Form
        },
        {
            path: 'processo-view',
            component: View
        },
        {
            path: 'processo-view/:codigo',
            component: View
        }]
}];
