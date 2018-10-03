

export class Process {
    constructor(
        public id: string,
        public number: string,
        public description: string
    ) {}

    public equals(obj: Process) : boolean { 
        return this.number === obj.number;
    } 

  }
  