import { EnumPermissaoUsuario } from './enumPermissaoUsuario';
export class Usuario {
    id: number;
    login: string;
    nome: string;
    senha: string;
    dataCriacao: Date;
    lstPermissao: Array<EnumPermissaoUsuario>;

    constructor() {}
}
