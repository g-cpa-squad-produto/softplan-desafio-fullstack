import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { PageNotFoundComponent } from './pages/page-not-found/page-not-found.component';
import { DashboardComponent } from './main/dashboard/dashboard.component';
import { ProcessesComponent } from './main/process/processes/processes.component';
import { ProcessFormComponent } from './main/process/process-form/process-form.component';
import { UsersComponent } from './main/user/users/users.component';
import { UserFormComponent } from './main/user/user-form/user-form.component';
import { LoginComponent } from './main/login/login.component';

import { AdminGuard } from './guards/admin.guard';
import { UserGuard } from './guards/user.guard';


const appRoutes: Routes = [
    { path: 'login', component: LoginComponent },
    { path: 'dashboard', component: DashboardComponent },
    {
        path: 'users', canActivate: [AdminGuard], canLoad: [AdminGuard],
        children: [
            { path: '', component: UsersComponent},
            { path: ':id', component: UserFormComponent }
        ]
    },
    {
        path: 'processes', canActivate: [UserGuard], canLoad: [UserGuard],
        children: [
            { path: '', component: ProcessesComponent},
            { path: ':id', component: ProcessFormComponent }
        ]
    },
    { path: '', component: DashboardComponent },
    { path: '**', component: PageNotFoundComponent }
];

@NgModule({
    imports: [
        RouterModule.forRoot(
            appRoutes,
            {
                onSameUrlNavigation: 'reload',
                useHash: true
            }
        )],
    exports: [RouterModule],
    providers: [Location]
})
export class AppRoutingModule { }