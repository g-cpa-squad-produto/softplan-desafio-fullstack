import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import {SearchUserComponent} from './components/user/search.user/search.user.component';
import {FormUserComponent} from './components/user/form.user/form.user.component';
import {SearchProcessComponent} from './components/process/search.process/search.process.component';
import {FormProcessComponent} from './components/process/form.process/form.process.component';
import {SearchLegalAdviceComponent} from './components/legal.advice/search.legal.advice/search.legal.advice.component';
import {FormLegalAdviceComponent} from './components/legal.advice/form.legal.advice/form.legal.advice.component';

const appRoutes: Routes = [
  {
    path: '',
    // component: LoginComponent
     component: SearchProcessComponent
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
  },

  {
    path: 'process',
    component: SearchProcessComponent
  },
  {
    path: 'process/form',
    component: FormProcessComponent
  },
  {
    path: 'process/form/:id',
    component: FormProcessComponent
  },

  {
    path: 'legalAdvice',
    component: SearchLegalAdviceComponent
  },
  {
    path: 'legalAdvice/form',
    component: FormLegalAdviceComponent
  },
  {
    path: 'legalAdvice/form/:id',
    component: FormLegalAdviceComponent
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
