import { Feedback } from './feedback';
import { StatusProcess } from './status-process';
export class Process {

  id: number;
  code: string;
  name: string;
  status: StatusProcess;
  feedbacks: Feedback[];

  constructor(init?: Partial<Process>) {
    if (init) {
      Object.assign(this, init);
    }
  }
}
