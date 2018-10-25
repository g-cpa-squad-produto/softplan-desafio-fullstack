import { Processo } from "./processo";
import { Usuario } from "./user";

export class Parecer {
    public id: number;
    public nome: string;
    public dataHora: Date;
    public processo: Processo;
    public usuario: Usuario;
}