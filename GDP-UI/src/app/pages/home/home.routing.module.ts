import { UserListComponent } from './../users/user-list/user-list.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { HomeComponent } from './home.component';
import { AuthGuard } from '../login/login.guard.service';
import { NewUserComponent } from '../users/new-user/new-user.component';
import { ShowUserComponent } from '../users/show-user/show-user.component';
import { FormUserComponent } from '../users/form-user/form-user.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    canActivate: [AuthGuard],
    children: [
      {
        path: '',
        component: HomeComponent
      },
      {
        path: 'usuarios',
        component: UserListComponent
      },
      {
        path: 'users/new',
        component: FormUserComponent
      },
      {
        path: 'users/:id/show',
        component: ShowUserComponent
      },
      {
        path: 'users/:id/update',
        component: FormUserComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomeRoutingModule {}
