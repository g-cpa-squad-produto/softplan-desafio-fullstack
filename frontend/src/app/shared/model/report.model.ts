import {User} from "./user.model";
import {Status} from "./enumerations/status.model";
import {Process} from "./process.model";

export class Report{
  constructor(
    public id: number,
    public description: string,
    public status: Status,
    public autor: User,
    public process: Process
  ) {}
}
