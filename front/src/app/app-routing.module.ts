import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { AboutComponent } from './pages/about/about.component';
import { Error404Component } from './pages/error404/error404.component';
import { LoginComponent } from './pages/login/login.component';
import { AuthGuard } from './services/auth/auth.guard';


const routes: Routes = [
  {
    path: 'home', component: HomeComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'about/:id', component: AboutComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'login', component: LoginComponent
  },
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  {
    path: '**', component: Error404Component
  },

];


@NgModule({
  exports: [RouterModule],
  imports: [RouterModule.forRoot(routes)],
  providers:[AuthGuard]
})

export class AppRoutingModule { }