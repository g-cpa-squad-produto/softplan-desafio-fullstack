import {Routes} from '@angular/router';
import {UsuarioForm} from './form/usuario-form';
import {List} from './list/list';
import {View} from './view/view';

export const UsuarioRoutes: Routes = [{
    path: '',
    redirectTo: 'usuario-list',
    pathMatch: 'full',
}, {
    path: '',
    children: [{
        path: 'usuario-list',
        component: List
    }, {
        path: 'usuario-form',
        component: UsuarioForm
    }, {
        path: 'usuario-form/:codigo',
        component: UsuarioForm
    }, {
        path: 'usuario-view',
        component: View
    }]
}];
