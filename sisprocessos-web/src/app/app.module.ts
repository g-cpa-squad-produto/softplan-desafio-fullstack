// registrando "pt-BR" como localização padrão da aplicação
import {registerLocaleData} from '@angular/common';
import localePtBr from '@angular/common/locales/pt';
import localePtBrExtra from '@angular/common/locales/extra/pt';

import {BrowserModule} from '@angular/platform-browser';
import {LOCALE_ID, NgModule} from '@angular/core';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HttpClientModule} from '@angular/common/http';

import {AppComponent} from './app.component';
import {MenuComponent} from './template/menu/menu.component';
import {ContentComponent} from './template/content/content.component';
import {FooterComponent} from './template/footer/footer.component';
import {DashboardComponent} from './dashboard/dashboard.component';
import {AppRoutingModule} from './app-routing.module';

registerLocaleData(localePtBr, 'pt-BR', localePtBrExtra);

@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    ContentComponent,
    FooterComponent,
    DashboardComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [
    {provide: LOCALE_ID, useValue: 'pt-BR'}
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
