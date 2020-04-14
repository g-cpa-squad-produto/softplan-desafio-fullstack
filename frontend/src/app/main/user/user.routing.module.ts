import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { UsersComponent } from './users/users.component';
import { UserFormComponent } from './user-form/user-form.component';

import { AdminGuard } from 'src/app/guards/admin.guard';


const usersRoutes: Routes = [
    {
        path: 'users', component: UsersComponent, canActivate: [AdminGuard], canLoad: [AdminGuard],
        children: [
            {
                path: ':id',
                component: UserFormComponent,
            }
        ]
    }
];

@NgModule({
    imports: [RouterModule.forChild(usersRoutes)],
    exports: [RouterModule]
})
export class UserRoutingModule { }
