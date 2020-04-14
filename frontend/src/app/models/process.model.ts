export class Process {
    id?: number;
    number?: string;
    report?: string;
    users?: any[];

    constructor(process) {
        this.number = process.number || '',
        this.number = process.number || '';
        this.report = process.report || '';
        this.users = process.users || '';
    }
}