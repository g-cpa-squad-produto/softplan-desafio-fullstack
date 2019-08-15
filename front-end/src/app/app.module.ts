import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatButtonModule } from '@angular/material';
import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import {
  LoginModule,
  LoginRoutingModule,
  ParecerModule,
  ProcessoModule,
  UsuarioModule,
  ParecerRoutingModule,
  ProcessoRoutingModule,
  UsuarioRoutingModule
} from './autenticacao';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MatButtonModule,
    LoginModule,
    LoginRoutingModule,
    ParecerModule,
    ProcessoModule,
    UsuarioModule,
    ParecerRoutingModule,
    ProcessoRoutingModule,
    UsuarioRoutingModule,

    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
