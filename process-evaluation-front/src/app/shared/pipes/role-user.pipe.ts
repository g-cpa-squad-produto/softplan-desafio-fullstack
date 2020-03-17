import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'roleUser'
})
export class RoleUserPipe implements PipeTransform {
  
  transform(value: any, ...args: any[]) {
    switch(value) {
      case 'ROLE_ADMIN':
        return 'Administrador';
      case 'ROLE_SCREENING':
        return 'Triador';
      case 'ROLE_CLOSER':
        return 'Finalizador';
    }
  }

}