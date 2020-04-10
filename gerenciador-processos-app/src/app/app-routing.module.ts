import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HomeComponent} from './pages/home/home.component';
import {ProcessoComponent} from './components/processo/processo-form/processo.component';
import {ProcessosComponent} from './components/processo/processos-list/processos.component';
import {UsuarioComponent} from './components/usuario/usuario-form/usuario.component';
import {UsuariosComponent} from './components/usuario/usuarios-list/usuarios.component';
import {NovoParecerComponent} from './components/processo/novo-parecer/novo-parecer.component';
import {IncluirUsuarioComponent} from './components/processo/incluir-usuario/incluir-usuario.component';
import {VisualizarProcessoComponent} from './components/processo/visualizar-processo/visualizar-processo.component';
import {UsuarioEditComponent} from './components/usuario/usuario-edit/usuario-edit.component';

const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', loadChildren: () => import('./pages/login/login.module').then(m => m.LoginModule)},
  { path: '', component: HomeComponent, children: [
      { path: 'processos/processo', component: ProcessoComponent },
      { path: 'processos', component: ProcessosComponent },
      { path: 'usuarios/usuario', component: UsuarioComponent },
      { path: 'usuarios/usuario/:id', component: UsuarioEditComponent },
      { path: 'usuarios', component: UsuariosComponent },
      { path: 'processos/visualizar-processo/:id', component: VisualizarProcessoComponent },
      { path: 'processos/novo-parecer/:id', component: NovoParecerComponent },
      { path: 'processos/incluir-usuarios/:id', component: IncluirUsuarioComponent },
    ]},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
