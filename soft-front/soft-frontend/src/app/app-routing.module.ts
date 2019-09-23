import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ControleUsuarioComponent} from './components/controle-usuario/controle-usuario.component';
import {ProcessoComponent} from './components/processo/processo.component';
import {IncluirFinalizadorComponent} from './components/incluir-finalizador/incluir-finalizador.component';
import {AdicionarParecerComponent} from './components/adicionar-parecer/adicionar-parecer.component';
import {ProcessosComponent} from './components/processo/processos/processos.component';


const routes: Routes = [
  {
    path: 'controle-usuario',
    component: ControleUsuarioComponent
  },
  {
    path: 'processo',
    component: ProcessoComponent
  },
  {
    path: 'incluir-finalizador',
    component: IncluirFinalizadorComponent
  },
  {
    path: 'adicionar-parecer',
    component: AdicionarParecerComponent
  },
  {
    path: 'processos',
    component: ProcessosComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
