import { UsuarioService } from './servicos/usuario.service';
import { AuthService } from './auth/auth.service';
import { AuthGuardService } from './auth/auth-guard.service';
import { JwtHelperService } from '@auth0/angular-jwt';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LogoutComponent } from './componentes/logout/logout.component';
import { UsuComponent } from './componentes/usuario/usu/usu.component';
import { ProcComponent } from './componentes/processo/proc/proc.component';

import { PendenteComponent } from './componentes/processo/pendentes/pendente/pendente.component';
import { ListUsuComponent } from './componentes/usuario/list-usu/list-usu.component';
import { ListProcComponent } from './componentes/processo/list-proc/list-proc.component';
import { ListPendenteComponent } from './componentes/processo/pendentes/list-pendente/list-pendente.component';
import { LoginComponent } from './componentes/login/login.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { HttpModule } from '@angular/http';
import { LogadoComponent } from './componentes/logado/logado.component';
import { AdmComponent } from './adm/adm.component';
import { ProcessoService } from './servicos/processo.service';

@NgModule({
  declarations: [
    AppComponent,
    LogoutComponent,
    UsuComponent,
    ProcComponent,
    PendenteComponent,
    ListUsuComponent,
    ListProcComponent,
    ListPendenteComponent,
    LoginComponent,
    LogadoComponent,
    AdmComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    JwtHelperService,
    AuthGuardService,
    AuthService,
    ProcessoService,
    UsuarioService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
