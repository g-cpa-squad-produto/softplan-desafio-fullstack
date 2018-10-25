import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AuthGuard } from './auth/auth.guard';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './pages/home/home.component';
import { ProcessosComponent } from './pages/processos/processos.component';
import { ParecerComponent } from './pages/parecer/parecer.component';

const routes: Routes = [
  { path: 'usuarios', component: HomeComponent, canActivate: [AuthGuard] },
  { path: 'processos', component: ProcessosComponent, canActivate: [AuthGuard] },
  { path: 'login', component: LoginComponent },
  { path: 'parecer', component: ParecerComponent },
  { path: '**', redirectTo: 'usuarios' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }