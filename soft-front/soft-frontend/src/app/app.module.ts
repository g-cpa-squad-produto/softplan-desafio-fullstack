import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {ControleUsuarioComponent} from './components/controle-usuario/controle-usuario.component';
import {ProcessoComponent} from './components/processo/processo.component';
import {IncluirFinalizadorComponent} from './components/incluir-finalizador/incluir-finalizador.component';
import {ProcessosComponent} from './components/processo/processos/processos.component';
import {AdicionarParecerComponent} from './components/adicionar-parecer/adicionar-parecer.component';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import {UsuarioService} from './core/services/usuario.service';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {ToastrModule} from 'ngx-toastr';
import {ProcessoService} from './core/services/processo.service';
import {NgxMaskModule} from 'ngx-mask';

@NgModule({
  declarations: [
    AppComponent,
    ControleUsuarioComponent,
    ProcessoComponent,
    IncluirFinalizadorComponent,
    ProcessosComponent,
    AdicionarParecerComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule,
    NgxMaskModule.forRoot(),
    ToastrModule.forRoot() // ToastrModule added
  ],
  providers: [UsuarioService, ProcessoService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
