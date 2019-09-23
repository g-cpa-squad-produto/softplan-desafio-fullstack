import {Usuario} from './pessoa.model';

export class Processo {
  id ?: number;
  email ?: string;
  dataCadastro ?: string;
  dataParecer ?: string;
  parecer ?: string;
  usuarios ?: Usuario[];
  situacao ?: string;
}
