import {Enum} from '../../shared/model/enum';

export class Usuario {
  id: number;
  nome: string;
  cpf: string;
  telefone: string;
  aniversario: Date;
  endereco: string;
  ativo: boolean;
  login: string;
  senha: string;
  perfil: Enum;
}
