import {Enum} from '../../shared/model/enum';
import {UsuarioResumido} from '../../usuarios/model/usuario-resumido';

export class Processo {
  id: number;
  numero: number;
  data: Date;
  descricao: string;
  parecer: string;
  usuarioParecer: UsuarioResumido;
  status: Enum;
  usuariosPermissao: Array<UsuarioResumido>;
}
