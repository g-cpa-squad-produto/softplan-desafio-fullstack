import { User } from './user';
import { Feedback } from './feedback';
import { StatusProcess } from './status-process';
export class Process {

  id: number;
  code: string;
  name: string;
  status: StatusProcess;
  feedbacks: Feedback[];
  users: User[];

  constructor(init?: Partial<Process>) {
    if (init) {
      Object.assign(this, init);
    }
  }
}
