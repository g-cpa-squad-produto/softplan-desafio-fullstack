import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { 
	UsuarioComponent, 
	CadastrarUsuarioComponent 
} from './components';

export const usuarioRoutes: Routes = [
	{
		path: 'cadastro-usuario',
		component: UsuarioComponent,
		children: [
		  {
			path: '', 
			component: CadastrarUsuarioComponent 
		  }
		]	
	}
];

@NgModule({
  imports: [
  	RouterModule.forChild(usuarioRoutes)
  ],
  exports: [
    RouterModule
  ]
})
export class UsuarioRoutingModule {
}


