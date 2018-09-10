import {NgModule} from '@angular/core';
import {SharedModule} from '../shared/shared.module';

import {ProcessosRoutingModule} from './processos-routing.module';
import {ListarProcessosComponent} from './component/listar-processos/listar-processos.component';
import {ManterProcessosComponent} from './component/manter-processos/manter-processos.component';
import {AtribuirUsuariosProcessosComponent} from './component/atribuir-usuarios-processos/atribuir-usuarios-processos.component';
import {IncluirParecerProcessosComponent} from './component/incluir-parecer-processos/incluir-parecer-processos.component';

@NgModule({
  imports: [
    SharedModule,
    ProcessosRoutingModule
  ],
  declarations: [
    ListarProcessosComponent,
    ManterProcessosComponent,
    AtribuirUsuariosProcessosComponent,
    IncluirParecerProcessosComponent
  ]
})
export class ProcessosModule {
}
