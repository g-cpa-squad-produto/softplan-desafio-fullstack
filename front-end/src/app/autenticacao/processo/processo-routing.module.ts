import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { 
	ProcessoComponent, 
	CadastrarProcessoComponent 
} from './components';

export const processoRoutes: Routes = [
	{
		path: 'cadastro-processo',
		component: ProcessoComponent,
		children: [
		  {
			path: '', 
			component: CadastrarProcessoComponent 
		  }
		]	
	}
];

@NgModule({
  imports: [
  	RouterModule.forChild(processoRoutes)
  ],
  exports: [
    RouterModule
  ]
})
export class ProcessoRoutingModule {
}


