CREATE TABLE IF NOT EXISTS processos.usuario (
  codigo INTEGER NOT NULL PRIMARY KEY,
  nome VARCHAR(50) NOT NULL,
  email VARCHAR(50) NOT NULL,
  senha VARCHAR(500) NOT NULL
);

CREATE TABLE IF NOT EXISTS processos.permissao (
  nome VARCHAR(50) NOT NULL PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS processos.usuario_permissao (
    codigo_usuario INTEGER NOT NULL,
    permissao VARCHAR(50) NOT NULL,
    FOREIGN KEY (codigo_usuario) REFERENCES processos.usuario (codigo),
    FOREIGN KEY (permissao) REFERENCES processos.permissao (nome),
    UNIQUE INDEX user_authority_idx_1 (codigo_usuario, permissao)
);