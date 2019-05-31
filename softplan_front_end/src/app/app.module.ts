import {BrowserModule} from '@angular/platform-browser';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import {ProcessoModule} from './processo/processo.module';

import {AppRoutes} from './app-routing.module';
import {AppComponent} from './app.component';
import {NgModule} from "@angular/core";
import {UsuarioModule} from "./usuario/usuario.module";

@NgModule({
    declarations: [
        AppComponent
    ],
    imports: [
        BrowserModule,
        FormsModule,
        HttpClientModule,
        ProcessoModule,
        UsuarioModule,
        RouterModule.forRoot(AppRoutes)
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
