import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {HeaderComponent} from './components/header/header.component';
import {SidebarComponent} from './components/sidebar/sidebar.component';
import {FooterComponent} from './components/footer/footer.component';
import {HomeComponent} from './components/home/home.component';
import {LoginComponent} from './components/security/login/login.component';
import {routes} from './app.routes';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {NgxWebstorageModule} from 'ngx-webstorage';
import {UserService} from './services/user.service';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {AuthInterceptor} from './components/security/auth.interceptor';
import {AuthGuard} from './components/security/auth.guard';
import {UserComponent} from './components/user/user.component';
import {UserNewComponent} from './components/user/user-new/user-new.component';
import {UserListComponent} from './components/user/user-list/user-list.component';
import {DialogService} from './dialog.service';
import {ProcessComponent} from './components/process/process.component';
import {ProcessListComponent} from './components/process/process-list/process-list.component';
import {ProcessNewComponent} from './components/process/process-new/process-new.component';
import {ReportComponent} from './components/report/report.component';
import {ReportListComponent} from './components/report/report-list/report-list.component';
import {ReportNewComponent} from './components/report/report-new/report-new.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    SidebarComponent,
    FooterComponent,
    HomeComponent,
    LoginComponent,
    UserComponent,
    UserNewComponent,
    UserListComponent,
    ProcessComponent,
    ProcessListComponent,
    ProcessNewComponent,
    ReportComponent,
    ReportListComponent,
    ReportNewComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    NgxWebstorageModule.forRoot(),
    routes,
    ReactiveFormsModule,
  ],
  providers: [
    UserService,
    AuthGuard,
    DialogService,
    {
      provide : HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
