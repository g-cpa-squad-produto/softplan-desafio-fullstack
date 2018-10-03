import { User } from './user';
import { Process } from './Process';


export class ProcessFeedback {
    constructor(
        public id: string,
        public feedback: string,
        public process: Process,
        public finalizator: User
    ) {}

    public equals(obj: ProcessFeedback) : boolean { 
        return this.process.id === obj.process.id && this.finalizator.id === obj.finalizator.id;
    } 

  }
  