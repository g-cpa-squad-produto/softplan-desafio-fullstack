import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { MaterializeModule } from 'angular2-materialize';
import { RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { UsuarioComponent } from './usuario/usuario.component';
import { ProcessoComponent } from './processo/processo.component';
import { AppRoutingModule } from 'src/app/app.routing';
import { HomeComponent } from './home/home.component';
import { ParecerComponent } from './parecer/parecer.component';

@NgModule({
  declarations: [
    AppComponent,
    UsuarioComponent,
    ProcessoComponent,
    HomeComponent,
    ParecerComponent
  ],
  imports: [
    BrowserModule,
    MaterializeModule,
    RouterModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
