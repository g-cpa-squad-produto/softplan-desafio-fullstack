import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { 
	ParecerComponent, 
	CadastrarParecerComponent 
} from './components';

export const parecerRoutes: Routes = [
	{
		path: 'cadastro-parecer',
		component: ParecerComponent,
		children: [
		  {
			path: '', 
			component: CadastrarParecerComponent 
		  }
		]	
	}
];

@NgModule({
  imports: [
  	RouterModule.forChild(parecerRoutes)
  ],
  exports: [
    RouterModule
  ]
})
export class ParecerRoutingModule {
}


