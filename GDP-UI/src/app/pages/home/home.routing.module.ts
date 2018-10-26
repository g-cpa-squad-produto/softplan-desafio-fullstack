import { ShowScreeningComponent } from './../screening/show-screening/show-screening.component';
import { ListScreeningComponent } from './../screening/list-screening/list-screening.component';
import { FinalizeGuard } from './../finalize/finalize.guard';
import { UserGuard } from './../users/user.guard';
import { UserListComponent } from './../users/user-list/user-list.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { HomeComponent } from './home.component';
import { AuthGuard } from '../../core/guard/auth.guard';
import { ShowUserComponent } from '../users/show-user/show-user.component';
import { FormUserComponent } from '../users/form-user/form-user.component';
import { ScreeningGuard } from '../screening/screening.guard';
import { FinalizeComponent } from '../finalize/finalize.component';
import { ListScreeningResolver } from '../screening/list-screening/list-screening.resolver';
import { ShowScreeningResolver } from '../screening/show-screening/show-screening.resolver';

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
        component: ListScreeningComponent,
        canActivate: [ScreeningGuard],
        resolve: {
          processList: ListScreeningResolver
        }
      },
      {
        path: 'triagem/:id/show',
        component: ShowScreeningComponent,
        canActivate: [ScreeningGuard],
        resolve: {
           process: ShowScreeningResolver
        }
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
