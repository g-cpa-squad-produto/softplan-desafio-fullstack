import {Enum} from '../../shared/model/enum';

export class UsuarioResumido {
  id: number;
  nome: string;
  cpf: string;
  aniversario: Date;
  ativo: boolean;
  perfil: Enum;
}
