// registrando "pt-BR" como localização padrão da aplicação
import {registerLocaleData} from '@angular/common';
import localePtBr from '@angular/common/locales/pt';
import localePtBrExtra from '@angular/common/locales/extra/pt';

import {BrowserModule} from '@angular/platform-browser';
import {LOCALE_ID, NgModule} from '@angular/core';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';

import {AppComponent} from './app.component';
import {MenuComponent} from './template/menu/menu.component';
import {ContentComponent} from './template/content/content.component';
import {FooterComponent} from './template/footer/footer.component';
import {AppRoutingModule} from './app-routing.module';
import {DashboardComponent} from './dashboard/dashboard.component';
import {AuthInterceptor} from './shared/auth/auth.interceptor';
import {LoginComponent} from './login/login.component';
import {SharedModule} from './shared/shared.module';

registerLocaleData(localePtBr, 'pt-BR', localePtBrExtra);

@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    ContentComponent,
    FooterComponent,
    DashboardComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    AppRoutingModule,
    SharedModule
  ],
  providers: [
    {provide: LOCALE_ID, useValue: 'pt-BR'},
    {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
