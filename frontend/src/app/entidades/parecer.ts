import { Processo } from './processo';
import { Usuario } from './usuario';
export class Parecer {
    id: number;
    parecer: string;
    dataCriacao: Date;
	usuarioCriacao: Usuario;
	processo: Processo;

    constructor() {}
}
