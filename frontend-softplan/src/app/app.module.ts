import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { UsuarioComponent } from './usuario/usuario.component';
import { UsuarioCreateComponent } from './usuario/usuario-create/usuario-create.component';
import { routing } from './app.routes';
import { HomeComponent } from './home/home.component';
import { UsuarioListagemComponent } from './usuario/usuario-listagem/usuario-listagem.component';
import { UsuarioService } from './usuario/usuario.service';
import { GenericServerService } from './generic-server.service';
import { UsuarioVisualizarComponent } from './usuario/usuario-visualizar/usuario-visualizar.component';
import { ProcessoComponent } from './processo/processo.component';
import { ProcessoListagemComponent } from './processo/processo-listagem/processo-listagem.component';
import { ProcessoCreateComponent } from './processo/processo-create/processo-create.component';
import { ProcessoVisualizarComponent } from './processo/processo-visualizar/processo-visualizar.component';
import { ParecerComponent } from './parecer/parecer.component';
import { ParecerCreateComponent } from './parecer/parecer-create/parecer-create.component';
import { ParecerListagemComponent } from './parecer/parecer-listagem/parecer-listagem.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ProcessoService } from './processo/processo.service';
import { ParecerService } from './parecer/parecer.service';
import { MaterialModule } from './material/material.module';
import { LoginComponent } from './login/login.component';
import { SharedService } from './login/shared.service';
import { ToolbarComponent } from './toolbar/toolbar.component';
import { MenusidenavComponent } from './menusidenav/menusidenav.component';

@NgModule({
  declarations: [
    AppComponent,
    UsuarioComponent,
    UsuarioCreateComponent,
    HomeComponent,
    UsuarioListagemComponent,
    UsuarioVisualizarComponent,
    ProcessoComponent,
    ProcessoListagemComponent,
    ProcessoCreateComponent,
    ProcessoVisualizarComponent,
    ParecerComponent,
    ParecerCreateComponent,
    ParecerListagemComponent,
    LoginComponent,
    ToolbarComponent,
    MenusidenavComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    HttpClientModule,
    FormsModule,
    routing,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    MaterialModule
  ],
  providers: [
    GenericServerService,
    UsuarioService,
    ProcessoService,
    ParecerService,
    SharedService ],
  bootstrap: [AppComponent]
})
export class AppModule { }
