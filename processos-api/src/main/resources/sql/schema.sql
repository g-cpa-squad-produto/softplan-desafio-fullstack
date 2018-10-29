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
    codigo_usuario BIGINT(20) NOT NULL,
    permissao VARCHAR(50) NOT NULL,
    FOREIGN KEY (codigo_usuario) REFERENCES processosdb.usuario (codigo),
    FOREIGN KEY (permissao) REFERENCES processosdb.permissao (nome),
    UNIQUE INDEX user_authority_idx_1 (codigo_usuario, permissao)
);

CREATE TABLE IF NOT EXISTS processosdb.processo (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	titulo VARCHAR(50) NOT NULL,
	descricao VARCHAR(500),
	data_criacao DATE
);