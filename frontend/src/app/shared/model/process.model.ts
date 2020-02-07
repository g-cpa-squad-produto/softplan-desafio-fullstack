import {User} from "./user.model";
import {Report} from "./report.model";

export class Process{
  constructor(
    public id: number,
    public title: string,
    public description: string,
    public reports: Report[],
    public autor: User
  ) {}
}
