import { User } from 'src/app/model/user';
import { Process } from './process';
export class Feedback {

  process: Process;
  user: User;
  description: string;

  constructor(init?: Partial<Feedback>) {
    if (init) {
      Object.assign(this, init);
    }
  }
}
