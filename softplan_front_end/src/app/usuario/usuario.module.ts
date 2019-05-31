import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';

import {List} from './list/list';
import {UsuarioForm} from './form/usuario-form';
import {View} from './view/view';
import {UsuarioRoutes} from './usuario.routing';

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        RouterModule.forChild(UsuarioRoutes)
    ],
    declarations: [
        List,
        UsuarioForm,
        View
    ]
})

export class UsuarioModule {
}
