import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';

import {UsuarioListComponent} from './list/usuario-list.component';
import {UsuarioFormComponent} from './form/usuario-form.component';
import {UsuarioDetailComponent} from './view/usuario-detail.component';
import {UsuarioRoutes} from './usuario.routing';

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        RouterModule.forChild(UsuarioRoutes)
    ],
    declarations: [
        UsuarioListComponent,
        UsuarioFormComponent,
        UsuarioDetailComponent
    ]
})

export class UsuarioModule {
}
