import { PerfilTypes } from './perfil-types';

export class User  {

  login: string;
  password: string;
  perfil: PerfilTypes;

  constructor(init?: Partial<User>) {
    if (init) {
      Object.assign(this, init);
    }
  }

}
