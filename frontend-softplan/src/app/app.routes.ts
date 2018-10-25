import { ModuleWithProviders } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { UsuarioComponent } from './usuario/usuario.component';
import { UsuarioCreateComponent } from './usuario/usuario-create/usuario-create.component';
import { UsuarioVisualizarComponent } from './usuario/usuario-visualizar/usuario-visualizar.component';
import { ProcessoComponent } from './processo/processo.component';
import { ProcessoCreateComponent } from './processo/processo-create/processo-create.component';
import { ProcessoVisualizarComponent } from './processo/processo-visualizar/processo-visualizar.component';
import { ParecerComponent } from './parecer/parecer.component';
import { ParecerCreateComponent } from './parecer/parecer-create/parecer-create.component';
import { HomeComponent } from './home/home.component';

const appRoutes: Routes = [
    { path: 'home', component: HomeComponent },
    { path: '', component: HomeComponent },
    { path: 'usuario', component: UsuarioComponent },
    { path: 'usuario/cadastro', component: UsuarioCreateComponent },
    { path: 'usuario/editar/:id', component: UsuarioCreateComponent },
    { path: 'usuario/visualizar/:id', component: UsuarioVisualizarComponent },
    { path: 'processo', component: ProcessoComponent },
    { path: 'processo/cadastro', component: ProcessoCreateComponent },
    { path: 'processo/editar/:id', component: ProcessoCreateComponent },
    { path: 'processo/visualizar/:id', component: ProcessoVisualizarComponent },
    { path: 'parecer', component: ParecerComponent },
    { path: 'parecer/adicionar/:processoId', component: ParecerCreateComponent }
];

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);
