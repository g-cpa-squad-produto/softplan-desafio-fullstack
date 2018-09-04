import { User } from "./user.model";

export class Process {
    constructor(
        public id: string,
        public number: number,
        public subject: string,
        public status: string,
        public priority: string,
        public attachment: string,
        public user: User,
        public assignedUser: User,
        public date: string,
        public reviews: Array<string>,
        public changes: Array<string>
    ) { }
}