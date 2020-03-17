import { Report } from '../report/report';

export class Process {
  idProcess: number;
  title: string;
  description: string;
  author: string;
  reports: Report[];
}