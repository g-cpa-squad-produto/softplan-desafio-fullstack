import { Parecer } from '../parecer/parecer.entity';

export class Processo {
    id: string;
    nome: string;
    numero: Number;
    ano: Number;
    cadastro: Number;
    parecer: Parecer;
}
