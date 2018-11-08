import { Usuario } from './usuario';
import { Parecer } from './parecer';

export class Processo {
    id: number;
    descricao: string;
    parecer: Parecer;
    lstUsuParecer: Array<Usuario>;
    dataCriacao: Date;

    constructor() {}
}
