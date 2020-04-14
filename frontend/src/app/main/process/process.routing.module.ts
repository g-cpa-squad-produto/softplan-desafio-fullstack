import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ProcessesComponent } from './processes/processes.component';
import { ProcessFormComponent } from './process-form/process-form.component';

import { UserGuard } from 'src/app/guards/user.guard';


const processesRoutes: Routes = [
    {
        path: 'processes', component: ProcessesComponent, canActivate: [ UserGuard ], canLoad: [UserGuard],
        children: [
            {
                path: ':id',
                component: ProcessFormComponent,
            }
        ]
    }
];

@NgModule({
    imports: [RouterModule.forChild(processesRoutes)],
    exports: [RouterModule]
})
export class ProcessRoutingModule { }
