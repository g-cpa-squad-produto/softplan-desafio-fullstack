import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { UsuarioComponent } from './usuario/usuario.component';
import { ProcessoComponent } from './processo/processo.component';
import { HomeComponent } from './home/home.component';
import { ParecerComponent } from './parecer/parecer.component';

export const appRoutes: Routes = [
  { path: 'usuario', component: UsuarioComponent },
  { path: 'processo', component: ProcessoComponent },
  { path: 'parecer', component: ParecerComponent },
  { path: '', component: HomeComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
