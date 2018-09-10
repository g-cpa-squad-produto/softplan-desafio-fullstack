import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ListarProcessosComponent} from './component/listar-processos/listar-processos.component';
import {ManterProcessosComponent} from './component/manter-processos/manter-processos.component';
import {ConsultarProcessoResolve} from './resolve/consultar-processo.resolve';
import {AtribuirUsuariosProcessosComponent} from './component/atribuir-usuarios-processos/atribuir-usuarios-processos.component';
import {IncluirParecerProcessosComponent} from './component/incluir-parecer-processos/incluir-parecer-processos.component';

const routes: Routes = [
  {path: 'listar', component: ListarProcessosComponent},
  {path: 'cadastrar', component: ManterProcessosComponent},
  {
    path: 'atribuir-usuarios/:idProcesso',
    component: AtribuirUsuariosProcessosComponent,
    resolve: {'processo': ConsultarProcessoResolve},
    runGuardsAndResolvers: 'always'
  },
  {
    path: 'incluir-parecer/:idProcesso',
    component: IncluirParecerProcessosComponent,
    resolve: {'processo': ConsultarProcessoResolve},
    runGuardsAndResolvers: 'always'
  },
  {
    path: ':idProcesso',
    component: ManterProcessosComponent,
    resolve: {'processo': ConsultarProcessoResolve},
    data: {readonly: true},
    runGuardsAndResolvers: 'always'
  },
  {path: '', redirectTo: 'listar'}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProcessosRoutingModule {
}
