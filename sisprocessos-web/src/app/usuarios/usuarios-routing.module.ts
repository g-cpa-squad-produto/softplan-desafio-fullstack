import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ListarUsuariosComponent} from './component/listar-usuarios/listar-usuarios.component';
import {ManterUsuariosComponent} from './component/manter-usuarios/manter-usuarios.component';
import {ConsultarUsuarioResolve} from './resolve/consultar-usuario.resolve';

const routes: Routes = [
  {path: 'listar', component: ListarUsuariosComponent},
  {path: 'cadastrar', component: ManterUsuariosComponent},
  {path: 'editar/:idUsuario', component: ManterUsuariosComponent, resolve: {'usuario': ConsultarUsuarioResolve}, runGuardsAndResolvers: 'always'},
  {
    path: ':idUsuario',
    component: ManterUsuariosComponent,
    resolve: {'usuario': ConsultarUsuarioResolve},
    data: {readonly: true},
    runGuardsAndResolvers: 'always'
  },
  {path: '', redirectTo: 'listar'}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UsuariosRoutingModule {
}
