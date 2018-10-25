import { NgModule } from '@angular/core';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppMaterialModule } from './app-material/app-material.module';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AuthGuard } from './auth/auth.guard';
import { HeaderComponent } from './header/header.component';
import { LoginComponent } from './login/login.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AuthService } from './service/auth.service';
import { TokenInterceptor } from './auth/jwt-interceptor';
import { HomeComponent } from './pages/home/home.component';
import { ProcessosComponent } from './pages/processos/processos.component';
import { ModalModule, BsModalRef } from 'ngx-bootstrap';
import { ParecerComponent } from './pages/parecer/parecer.component';
import { Page404Component } from './pages/page404/page404.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    HeaderComponent,
    LoginComponent,
    ProcessosComponent,
    ParecerComponent,
    Page404Component
  ],
  imports: [
    ModalModule.forRoot(),
    HttpClientModule,
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    BrowserAnimationsModule,
    AppMaterialModule
  ],
  providers: [
    BsModalRef,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    },
    AuthService,
    AuthGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }