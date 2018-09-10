import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { MenuComponent } from './components/menu/menu.component';
import { FooterComponent } from './components/footer/footer.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/security/login/login.component';
import { routes } from './app.routes';
import { UserService } from './services/user.service';
import { SharedService } from './services/shared.service';
import { AuthInterceptor } from './components/security/auth.interceptor';
import { AuthGuard } from './components/security/auth.guard';
import { UserNewComponent } from './components/user-new/user-new.component';
import { UserListComponent } from './components/user-list/user-list.component';
import { DialogService } from './dialog.service';
import { ProcessService } from './services/process.service';
import { ProcessNewComponent } from './components/process-new/process-new.component';
import { ProcessListComponent } from './components/process-list/process-list.component';
import { ProcessDetailComponent } from './components/process-detail/process-detail.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    MenuComponent,
    FooterComponent,
    HomeComponent,
    LoginComponent,
    UserNewComponent,
    UserListComponent,
    ProcessNewComponent,
    ProcessListComponent,
    ProcessDetailComponent,
    DashboardComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    routes
  ],
  providers: [
    UserService, 
    SharedService,
    DialogService,
    ProcessService,
    AuthGuard,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    }],    
  bootstrap: [AppComponent]
})
export class AppModule { }
