import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { FormUsuarioComponent } from './usuario/form-usuario/form-usuario.component';
import { ProcessoComponent } from './processo/processo.component';
import { HomeComponent } from './home/home.component';
import { ParecerComponent } from './parecer/parecer.component';
import { ConsultaUsuarioComponent } from './usuario/consulta-usuario/consulta-usuario.component';

export const appRoutes: Routes = [
  { path: 'usuario/consulta', component: ConsultaUsuarioComponent },
  { path: 'usuario/form', component: FormUsuarioComponent },
  { path: 'processo', component: ProcessoComponent },
  { path: 'parecer', component: ParecerComponent },
  { path: '', component: HomeComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
