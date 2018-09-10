import {NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {DashboardComponent} from './dashboard/dashboard.component';

@NgModule({
  imports: [
    RouterModule.forRoot([
      {
        path: '', component: DashboardComponent
      },
      {
        path: 'processos',
        loadChildren: 'app/processos/processos.module#ProcessosModule'
      },
      {
        path: 'usuarios',
        loadChildren: 'app/usuarios/usuarios.module#UsuariosModule'
      }
    ])
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule {
}
