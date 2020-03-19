import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './presentation/login/login.component';
import { HomeComponent } from './presentation/authenticated/home/home.component';
import { LogoutComponent } from './presentation/logout/logout.component';
import { TemplateViewComponent } from './presentation/authenticated/template-view/template-view.component';


const routes: Routes = [
  { 
    path: "login", 
    component: LoginComponent },
  { 
    path: "logout", 
    component: LogoutComponent },
  // {
  //   path: "",
  //   component: TemplateViewComponent,
  //   children: [
      {
        path: "",
        redirectTo: "home",
        pathMatch: "full"
      },
      {
        path: 'home',
        component: HomeComponent,
        children: [
        ]
      },
  //   ]
  // },

  // otherwise redirect to home
  { path: "**", redirectTo: "" }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
