import {NgModule} from '@angular/core';

import {UsuariosRoutingModule} from './usuarios-routing.module';
import {ListarUsuariosComponent} from './component/listar-usuarios/listar-usuarios.component';
import {ManterUsuariosComponent} from './component/manter-usuarios/manter-usuarios.component';
import {SharedModule} from '../shared/shared.module';

@NgModule({
  imports: [
    SharedModule,
    UsuariosRoutingModule
  ],
  declarations: [
    ListarUsuariosComponent,
    ManterUsuariosComponent
  ]
})
export class UsuariosModule {
}
