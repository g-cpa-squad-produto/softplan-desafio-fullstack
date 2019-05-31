export interface UsuarioDto {
    id: Number;
    dataCriacao: Date;
    dataModificacao: Date;

    createdBy: String;

    login: String;
    senha: String;
    email: String;
    nomeCompleto: String,
    permissao: String
}
