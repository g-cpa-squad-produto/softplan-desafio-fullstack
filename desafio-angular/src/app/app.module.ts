import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { MaterializeModule } from 'angular2-materialize';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app.routing';
import { HomeComponent } from './home/home.component';
import { ParecerComponent } from './parecer/parecer.component';
import { UsuarioModule } from './usuario/usuario.module';
import { ProcessoModule } from './processo/processo.module';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ParecerComponent
  ],
  imports: [
    BrowserModule,
    MaterializeModule,
    RouterModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    UsuarioModule,
    ProcessoModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
