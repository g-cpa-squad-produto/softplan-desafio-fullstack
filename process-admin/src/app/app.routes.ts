import { Routes, RouterModule } from "@angular/router";
import { ModuleWithProviders } from "@angular/core";
import { LoginComponent } from "./components/security/login/login.component";
import { HomeComponent } from "./components/home/home.component";
import { AuthGuard } from "./components/security/auth.guard";
import { UserNewComponent } from "./components/user-new/user-new.component";
import { UserListComponent } from "./components/user-list/user-list.component";
import { ProcessNewComponent } from "./components/process-new/process-new.component";
import { ProcessListComponent } from "./components/process-list/process-list.component";
import { ProcessDetailComponent } from "./components/process-detail/process-detail.component";
import { DashboardComponent } from "./components/dashboard/dashboard.component";

export const ROUTES: Routes = [
  { path: "", component: HomeComponent, canActivate: [AuthGuard] },
  { path: "login", component: LoginComponent },
  { path: "dashboard", component: DashboardComponent, canActivate: [AuthGuard] },
  { path: "user-list", component: UserListComponent, canActivate: [AuthGuard] },
  { path: "user-new", component: UserNewComponent, canActivate: [AuthGuard] },
  { path: "user-new/:id", component: UserNewComponent, canActivate: [AuthGuard] },
  { path: "process-list", component: ProcessListComponent, canActivate: [AuthGuard] },
  { path: "process-new", component: ProcessNewComponent, canActivate: [AuthGuard] },
  { path: "process-new/:id", component: ProcessNewComponent, canActivate: [AuthGuard] },
  { path: "process-detail/:id", component: ProcessDetailComponent, canActivate: [AuthGuard] }
];

export const routes: ModuleWithProviders = RouterModule.forRoot(ROUTES);