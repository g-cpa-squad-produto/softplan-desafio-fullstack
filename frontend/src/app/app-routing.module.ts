import { AdmComponent } from './adm/adm.component';
import { AuthGuardService } from './auth/auth-guard.service';
import { ListPendenteComponent } from './componentes/processo/pendentes/list-pendente/list-pendente.component';
import { ListProcComponent } from './componentes/processo/list-proc/list-proc.component';
import { ListUsuComponent } from './componentes/usuario/list-usu/list-usu.component';
import { PendenteComponent } from './componentes/processo/pendentes/pendente/pendente.component';
import { ProcComponent } from './componentes/processo/proc/proc.component';
import { UsuComponent } from './componentes/usuario/usu/usu.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './componentes/login/login.component';
import { LogoutComponent } from './componentes/logout/logout.component';
import { LogadoComponent } from './componentes/logado/logado.component';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'logout', component: LogoutComponent },
  { path: '', component: AdmComponent,
        children: [
          { path: 'logado', component: LogadoComponent, canActivate: [AuthGuardService]  },
          { path: 'usuario', component: UsuComponent, canActivate: [AuthGuardService]  },
          { path: 'usuario/:id', component: UsuComponent, canActivate: [AuthGuardService]  },
          { path: 'listar-usuario', component: ListUsuComponent, canActivate: [AuthGuardService]  },
          { path: 'processo', component: ProcComponent, canActivate: [AuthGuardService]  },
          { path: 'processo/:id', component: ProcComponent, canActivate: [AuthGuardService]  },
          { path: 'listar-processo', component: ListProcComponent, canActivate: [AuthGuardService]  },
          { path: 'listar-pendente', component: ListPendenteComponent, canActivate: [AuthGuardService]  },
          { path: 'pendente/:idProc', component: PendenteComponent, canActivate: [AuthGuardService]  }
        ]
    },
   // Outras tentativas
   { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
