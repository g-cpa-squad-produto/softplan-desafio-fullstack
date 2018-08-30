import {LegalAdvice} from '../legal.advice/legal.advice.model'
export class Process {
    id: string
    code: string
    summary: string
    description: string
    idUserCreatedBy: number
    idUserFinishedBy:number
    loginCreatedBy: string
    loginFinishedBy: string
    legalAdvices: LegalAdvice[]
}
