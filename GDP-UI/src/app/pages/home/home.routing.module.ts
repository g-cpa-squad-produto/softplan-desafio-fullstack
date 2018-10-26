import { FinalizeGuard } from './../finalize/finalize.guard';
import { UserGuard } from './../users/user.guard';
import { UserListComponent } from './../users/user-list/user-list.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { HomeComponent } from './home.component';
import { AuthGuard } from '../../core/guard/auth.guard';
import { ShowUserComponent } from '../users/show-user/show-user.component';
import { FormUserComponent } from '../users/form-user/form-user.component';
import { ScreeningComponent } from '../screening/screening.component';
import { ScreeningGuard } from '../screening/screening.guard';
import { FinalizeComponent } from '../finalize/finalize.component';

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
        component: UserListComponent,
        canActivate: [UserGuard],
      },
      {
        path: 'users/new',
        component: FormUserComponent,
        canActivate: [UserGuard],
      },
      {
        path: 'users/:id/show',
        component: ShowUserComponent,
        canActivate: [UserGuard],
      },
      {
        path: 'users/:id/update',
        component: FormUserComponent,
        canActivate: [UserGuard],
      },
      {
        path: 'triagem',
        component: ScreeningComponent,
        canActivate: [ScreeningGuard],
      },
      {
        path: 'finalizar',
        component: FinalizeComponent,
        canActivate: [FinalizeGuard],
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomeRoutingModule {}
