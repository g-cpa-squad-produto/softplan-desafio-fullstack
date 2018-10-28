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
import { ListScreeningResolver } from '../screening/list-screening/list-screening.resolver';
import { ShowScreeningResolver } from '../screening/show-screening/show-screening.resolver';
import { FormScreeningComponent } from '../screening/form-screening/form-screening.component';
import { FormAssignComponent } from '../screening/form-assign/form-assign.component';
import { ListFinalizeComponent } from '../finalize/list-finalize/list-finalize.component';
import { ShowFinalizeComponent } from '../finalize/show-finalize/show-finalize.component';
import { ShowFinalizeResolver } from '../finalize/show-finalize/show-finalize.resolver';
import { FormFinalizeComponent } from '../finalize/form-finalize/form-finalize.component';

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
        path: 'triagem/new',
        component: FormScreeningComponent,
        canActivate: [ScreeningGuard]
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
        path: 'triagem/:id/update',
        component: FormScreeningComponent,
        canActivate: [ScreeningGuard]
      },
      {
        path: 'triagem/:id/atribuir',
        component: FormAssignComponent,
        canActivate: [ScreeningGuard],
        resolve: {
          process: ShowScreeningResolver
       }
      },
      {
        path: 'finalizar',
        component: ListFinalizeComponent,
        canActivate: [FinalizeGuard],
        resolve: {
          processList: ListScreeningResolver
        }
      },
      {
        path: 'finalizar/:id/show',
        component: ShowFinalizeComponent,
        canActivate: [FinalizeGuard],
        resolve: {
          process: ShowScreeningResolver,
          feedbaks: ShowFinalizeResolver
        }

      },
      {
        path: 'finalizar/:id/parecer',
        component: FormFinalizeComponent,
        canActivate: [FinalizeGuard],
        resolve: {
          process: ShowScreeningResolver,
          feedbaks: ShowFinalizeResolver
        }
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomeRoutingModule {}
