import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import {SearchUserComponent} from './components/user/search.user/search.user.component';
import {FormUserComponent} from './components/user/form.user/form.user.component';
import {LoginComponent} from './components/login/login.component';

import { OnlyForDevComponent } from './components/only-for-dev/only-for-dev.component';

const appRoutes: Routes = [
  {
    path: '',
     component: LoginComponent
    // component: OnlyForDevComponent
  },
  {
    path: 'user',
    component: SearchUserComponent
  },
  {
    path: 'user/form',
    component: FormUserComponent
  }
];

@NgModule({
  imports: [
    RouterModule.forRoot(
      appRoutes
    )
  ],
  exports: [
    RouterModule
  ],
  providers: []
})

export class AppRoutingModule { }
