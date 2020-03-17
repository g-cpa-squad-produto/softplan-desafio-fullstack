import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { AppRoutingModule } from './app-routing.module';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { AppComponent } from './app.component';
import { ToastComponent } from './shared/toast/toast.component';
import { LoginComponent } from './core/login/login.component';
import { UsersInitComponent } from './core/login/users-init/users-init.component';
import { HeaderComponent } from './core/header/header.component';
import { HomeComponent } from './home/home.component';
import { HomeAdminComponent } from './home/home-admin/home-admin.component';
import { HomeScreeningComponent } from './home/home-screening/home-screening.component';
import { ProcessComponent } from './process/process.component';
import { UserReportComponent } from './report/user-report/user-report.component';
import { RoleUserPipe } from './shared/pipes/role-user.pipe';

import { AuthInterceptor } from './core/auth/auth.interceptor';
import { UserComponent } from './user/user.component';

import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { HomeCloserComponent } from './home/home-closer/home-closer.component';
import { CommentReportComponent } from './report/comment-report/comment-report.component';

@NgModule({
  declarations: [
    AppComponent,
    ToastComponent,
    LoginComponent,
    UsersInitComponent,
    HeaderComponent,
    HomeComponent,
    HomeAdminComponent,
    UserComponent,
    HomeScreeningComponent,
    ProcessComponent,
    UserReportComponent,
    HomeCloserComponent,
    CommentReportComponent,
    RoleUserPipe
  ],
  imports: [
    CommonModule,
    BrowserModule,
    HttpClientModule,
    RouterModule,
    FormsModule,
    ReactiveFormsModule, 
    NgbModule,
    FontAwesomeModule,
    AppRoutingModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
