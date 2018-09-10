import {Enum} from '../../shared/model/enum';

export class ProcessoResumido {
  id: number;
  numero: number;
  data: Date;
  descricao: string;
  status: Enum;
}
