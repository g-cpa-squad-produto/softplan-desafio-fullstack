export interface ProcessoDto {
    isNew: Boolean,
    id: Number;
    dataCriacao: Date;
    dataFinalizacao: Date;

    titulo: String;
    descricao: String;

    createdBy: String;
    finalizadores: Array<String>;
    finalizadoresEscolhidos: Array<String>;
}
