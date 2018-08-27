import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import {SearchUserComponent} from './components/user/search.user/search.user.component';
import {FormUserComponent} from './components/user/form.user/form.user.component';

const appRoutes: Routes = [
  {
    path: '',
    // component: LoginComponent
     component: SearchUserComponent //OnlyForDevComponent
  },
  {
    path: 'user',
    component: SearchUserComponent
  },
  {
    path: 'user/form',
    component: FormUserComponent
  },
  {
    path: 'user/form/:id',
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
