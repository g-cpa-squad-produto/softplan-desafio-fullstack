import { FooterComponent } from './components/footer/footer.component';
import { Routes, RouterModule } from "@angular/router";
import { LoginComponent } from "./components/security/login/login.component";
import { HomeComponent } from './components/home/home.component';
import { ModuleWithProviders } from "@angular/core";
import { AuthGuard } from './components/security/auth.guard';
import { UserNewComponent } from './components/user-new/user-new.component';
import { UserListComponent } from './components/user-list/user-list.component';


export const ROUTES: Routes = [
  { path: 'login' , component: LoginComponent },
  { path: '' , component:  HomeComponent, canActivate: [AuthGuard]},
  { path: 'user-new' , component: UserNewComponent, canActivate: [AuthGuard] },
  { path: 'user-new/:id' , component: UserNewComponent, canActivate: [AuthGuard] },
  { path: 'user-list' , component: UserListComponent, canActivate: [AuthGuard] },
]

export const routes: ModuleWithProviders = RouterModule.forRoot(ROUTES);

