import { User } from "./user.model";
import { Process } from "./process.model";

export class ProcessReview {
    constructor(
        public id: string,
        public process: Process,
        public user: User,
        public dateReview: string,
        public description: string
    ){}
}