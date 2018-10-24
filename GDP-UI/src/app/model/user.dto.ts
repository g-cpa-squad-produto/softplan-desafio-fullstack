import { ProfileTypes } from './profile-types';

export class UserDTO {
  login: string;
  profile: ProfileTypes;
  token: string;

  constructor(init?: Partial<UserDTO>) {
    if (init) {
      Object.assign(this, init);
    }
  }
}
