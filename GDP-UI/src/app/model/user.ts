import { PerfilTypes } from './perfil-types';

export class User  {
  id: number;
  login: string;
  password: string;
  name: string;
  lastName: string;
  profile: PerfilTypes;

  constructor(init?: Partial<User>) {
    if (init) {
      Object.assign(this, init);
    }
  }

}
