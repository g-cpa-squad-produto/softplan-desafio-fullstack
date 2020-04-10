import {UsuariosVinculados} from './usuarios-vinculados.model';

export class NovoUsuarioProcesso {
  public idProcesso: number;
  public usuariosVinculados: Array<UsuariosVinculados>;
  constructor(idProcesso, usuariosVinculados) {
    this.idProcesso = idProcesso;
    this.usuariosVinculados = usuariosVinculados;
  }
}
