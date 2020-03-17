import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './core/login/login.component';
import { HomeComponent } from './home/home.component';
import { GuardService } from './core/guard/guard.service';
import { UserComponent } from './user/user.component';
import { ProcessComponent } from './process/process.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'home', component: HomeComponent, canActivate: [GuardService] },
  { path: 'user', component: UserComponent, canActivate: [GuardService], data: {role: 'ROLE_ADMIN'} },
  { path: 'user/:id', component: UserComponent, canActivate: [GuardService], data: {role: 'ROLE_ADMIN'} },
  { path: 'process', component: ProcessComponent, canActivate: [GuardService], data: {role: 'ROLE_SCREENING'}},
  { path: 'process/:id', component: ProcessComponent, canActivate: [GuardService], data: {role: 'ROLE_SCREENING'}},
  { path: '', pathMatch: 'full', redirectTo: 'home'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }


