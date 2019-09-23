export class Pessoa {
  nome?: string;
  emailLogin?: string;
  cnpj?: string;
  cpf?: string;
  id_pessoa ?: number;

}

export class Usuario extends Pessoa {
  id ?: number;
  senhaAcesso?: string;
  tipoUsuario?: string;
}
