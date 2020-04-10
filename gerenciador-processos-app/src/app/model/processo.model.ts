import {UsuariosVinculados} from './usuarios-vinculados.model';
import {Parecer} from './parecer.model';

export class Processo {
  public id: number;
  public descricao: string;
  public usuariosVinculados: Array<UsuariosVinculados>;
  public dataRegistro: any;
  public parecer: Parecer;
  public statusProcesso: string;
}
