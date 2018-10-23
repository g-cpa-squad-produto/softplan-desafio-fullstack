import { PerfilTypes } from 'src/app/model/perfil-types';
export class Token  {

  value: string;
  login: string;
  perfil: PerfilTypes;

  constructor(value) {
     this.value = value;
  }

}
