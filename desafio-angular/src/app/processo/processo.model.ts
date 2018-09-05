import { Usuario } from './../usuario/usuario.model';
export class Processo {
  constructor(
    public id?: Number,
    public numero?: string,
    public dados?: string,
    public pendente?: string,
    public finalizadores?: Usuario[],
  ) { }
}
