import {NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {DashboardComponent} from './dashboard/dashboard.component';
import {LoginComponent} from './login/login.component';
import {AuthGuard} from './shared/auth/auth.guard';

@NgModule({
  imports: [
    RouterModule.forRoot([
      {
        path: '', component: DashboardComponent, canActivate: [AuthGuard]
      },
      {
        path: 'login', component: LoginComponent
      },
      {
        path: 'processos',
        loadChildren: 'app/processos/processos.module#ProcessosModule',
        canActivate: [AuthGuard]
      },
      {
        path: 'usuarios',
        loadChildren: 'app/usuarios/usuarios.module#UsuariosModule',
        canActivate: [AuthGuard]
      }
    ])
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule {
}
