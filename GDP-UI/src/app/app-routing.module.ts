import { ListTableModule } from './components/list-table/list-table.module';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ListTableComponent } from './components/list-table/list-table.component';

const routes: Routes = [
  { path: '', component: ListTableComponent },
  { path: '**', redirectTo: '/' }
];

@NgModule({
  imports: [
    ListTableModule,
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
