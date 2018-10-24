CREATE TABLE IF NOT EXISTS processosdb.usuario (
  codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  email VARCHAR(100) NOT NULL UNIQUE,
  nome VARCHAR(50) NOT NULL,
  senha VARCHAR(500) NOT NULL
);

CREATE TABLE IF NOT EXISTS processosdb.permissao (
  nome VARCHAR(50) NOT NULL PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS processosdb.usuario_permissao (
    email_usuario VARCHAR(100) NOT NULL,
    permissao VARCHAR(50) NOT NULL,
    FOREIGN KEY (email_usuario) REFERENCES processosdb.usuario (email),
    FOREIGN KEY (permissao) REFERENCES processosdb.permissao (nome),
    UNIQUE INDEX user_authority_idx_1 (email_usuario, permissao)
);