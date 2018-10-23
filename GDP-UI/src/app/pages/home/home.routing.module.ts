import { ContainerGuard } from './../../components/container/container.gard.service';
import { UserResolver } from './../../core/resolver/user.resolver';
import { ListTableComponent } from './../../components/list-table/list-table.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { HomeComponent } from './home.component';
import { AuthGuard } from '../login/login.guard.service';
import { UserListComponent } from '../users/user-list/user-list.component';
import { ContainerComponent } from 'src/app/components/container/container.component';

const routes: Routes = [
    {
        path: '',
        component: HomeComponent,
        canActivate: [AuthGuard],
        resolve: {
          user: UserResolver
        },
        children: [
            {
                path: '',
                component: ContainerComponent,
                canActivate: [ContainerGuard],
                resolve: {
                  user: UserResolver
                },
            },
            {
              path: 'users',
              component: ListTableComponent,
              canActivate: [ContainerGuard],
              resolve: {
                user: UserResolver
              },
          }
        ]
    },
];

@NgModule({
    imports: [
        RouterModule.forChild(routes)
    ],
    exports: [ RouterModule ]
})
export class HomeRoutingModule { }

