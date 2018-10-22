import { UserResolver } from './../../core/resolver/user.resolver';
import { ListTableComponent } from './../../components/list-table/list-table.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { HomeComponent } from './home.component';
import { AuthGuard } from '../login/login.guard.service';
import { UserListComponent } from '../users/user-list/user-list.component';

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
                component: ListTableComponent,
            },
            {
              path: 'users',
              component: UserListComponent,
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

