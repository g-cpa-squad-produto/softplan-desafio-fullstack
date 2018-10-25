import { Usuario } from "./user";

export class Processo {
    public id: number;
    public nome: string;
    public dataHora: Date;
    public usuarios: [] = [];
    public finalizado: Boolean = false;
}