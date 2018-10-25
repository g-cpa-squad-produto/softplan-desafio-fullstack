import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AuthGuard } from './auth/auth.guard';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './pages/home/home.component';
import { ProcessosComponent } from './pages/processos/processos.component';
import { ParecerComponent } from './pages/parecer/parecer.component';
import { Page404Component } from './pages/page404/page404.component';

const routes: Routes = [
  {
    path: 'usuarios', component: HomeComponent, canActivate: [AuthGuard], data: {
      expectedRole: ['ROLE_ADMINISTRADOR']
    }
  },
  {
    path: 'processos', component: ProcessosComponent, canActivate: [AuthGuard], data: {
      expectedRole: ['ROLE_USUARIO_TRIADOR']
    }
  },
  {
    path: 'parecer', component: ParecerComponent, canActivate: [AuthGuard], data: {
      expectedRole: ['ROLE_USUARIO_FINALIZADOR']
    }
  },
  { path: 'login', component: LoginComponent },

  { path: '404', component: Page404Component },
  { path: '**', redirectTo: '404' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }