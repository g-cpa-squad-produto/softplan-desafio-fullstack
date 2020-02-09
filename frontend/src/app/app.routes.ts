import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from './components/home/home.component';
import {LoginComponent} from './components/security/login/login.component';
import {ModuleWithProviders} from '@angular/core';
import {AuthGuard} from './components/security/auth.guard';
import {UserNewComponent} from "./components/user/user-new/user-new.component";

export const ROUTES: Routes = [
  {path: '', component: LoginComponent},
  {path: 'dashboard', component: HomeComponent,  canActivate: [AuthGuard]},
  {path: 'user-new', component: UserNewComponent,  canActivate: [AuthGuard]}
];

export const routes: ModuleWithProviders = RouterModule.forRoot(ROUTES);
