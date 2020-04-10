import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/common/navbar/navbar.component';
import { HomeComponent } from './pages/home/home.component';
import { MenuComponent } from './components/common/menu/menu.component';
import { ProcessoComponent } from './components/processo/processo-form/processo.component';
import { ProcessosComponent } from './components/processo/processos-list/processos.component';
import { UsuarioComponent } from './components/usuario/usuario-form/usuario.component';
import { UsuariosComponent } from './components/usuario/usuarios-list/usuarios.component';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {NgxPaginationModule} from 'ngx-pagination';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { ModalComponent } from './modal/modal.component';
import { NovoParecerComponent } from './components/processo/novo-parecer/novo-parecer.component';
import { IncluirUsuarioComponent } from './components/processo/incluir-usuario/incluir-usuario.component';
import { VisualizarProcessoComponent } from './components/processo/visualizar-processo/visualizar-processo.component';
import { UsuarioEditComponent } from './components/usuario/usuario-edit/usuario-edit.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    HomeComponent,
    MenuComponent,
    ProcessoComponent,
    ProcessosComponent,
    UsuarioComponent,
    UsuariosComponent,
    ModalComponent,
    NovoParecerComponent,
    IncluirUsuarioComponent,
    VisualizarProcessoComponent,
    UsuarioEditComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    NgxPaginationModule,
    NgbModule,
    FontAwesomeModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
