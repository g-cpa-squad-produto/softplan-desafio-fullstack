import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { MaterializeModule } from 'angular2-materialize';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { ProcessoComponent } from './processo/processo.component';
import { AppRoutingModule } from './app.routing';
import { HomeComponent } from './home/home.component';
import { ParecerComponent } from './parecer/parecer.component';
import { UsuarioModule } from './usuario/usuario.module';

@NgModule({
  declarations: [
    AppComponent,
    ProcessoComponent,
    HomeComponent,
    ParecerComponent
  ],
  imports: [
    BrowserModule,
    MaterializeModule,
    RouterModule,
    AppRoutingModule,
    HttpClientModule,
    UsuarioModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
