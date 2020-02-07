import {Role} from "./enumerations/role.model";
import {Report} from "./report.model";
import {Process} from "./process.model";

export class User {
  constructor(
    public id: number,
    public login: string,
    public password: string,
    public firstName: string,
    public lastName: string,
    public email: string,
    public role: Role,
    public reports: Report[],
    public processes: Process[]
  ){}
}
