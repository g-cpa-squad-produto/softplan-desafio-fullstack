import {RouterModule, Routes} from "@angular/router";
import {HomeComponent} from "./components/home/home.component";
import {LoginComponent} from "./components/security/login/login.component";
import {ModuleWithProviders} from "@angular/core";

export const ROUTES: Routes = [
  {path: '', component: LoginComponent},
  {path: 'login', component: HomeComponent}
]

export const routes: ModuleWithProviders = RouterModule.forRoot(ROUTES);
