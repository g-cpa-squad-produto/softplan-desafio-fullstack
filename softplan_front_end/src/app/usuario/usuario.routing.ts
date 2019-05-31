import {Routes} from '@angular/router';
import {UsuarioFormComponent} from './form/usuario-form.component';
import {UsuarioListComponent} from './list/usuario-list.component';
import {UsuarioDetailComponent} from './view/usuario-detail.component';

export const UsuarioRoutes: Routes = [{
    path: '',
    redirectTo: 'usuario-list',
    pathMatch: 'full',
}, {
    path: '',
    children: [{
        path: 'usuario-list',
        component: UsuarioListComponent
    }, {
        path: 'usuario-form',
        component: UsuarioFormComponent
    }, {
        path: 'usuario-form/:codigo',
        component: UsuarioFormComponent
    }, {
        path: 'usuario-view',
        component: UsuarioDetailComponent
    }]
}];
