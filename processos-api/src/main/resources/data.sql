DROP TABLE IF EXISTS usuario;
DROP TABLE IF EXISTS processo;
DROP TABLE IF EXISTS parecer;

CREATE TABLE usuario (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(250) NOT NULL,
  email VARCHAR(250) NOT NULL,
  tipo VARCHAR(32) DEFAULT NULL,
  situacao VARCHAR(32) DEFAULT NULL
);

INSERT INTO usuario (nome, email, tipo, situacao) VALUES
  ('José de Freitas', 'josefreitas@tj.com.br', 'ADMINISTRADOR', 'ATIVO'),
  ('João da Silva', 'joaosilva@tj.com.br', 'TRIADOR', 'ATIVO'),
  ('Maria da Glória', 'mariagloria@tj.com.br', 'FINALIZADOR', 'ATIVO');

CREATE TABLE processo (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(250) NOT NULL,
  texto VARCHAR(4000) NOT NULL,
  us_id INT NOT NULL,
  data_criacao DATE NOT NULL,
  FOREIGN KEY(us_id) REFERENCES usuario(id)
);

CREATE TABLE parecer (
  id INT AUTO_INCREMENT PRIMARY KEY,
  texto VARCHAR(4000),
  situacao VARCHAR(32) NOT NULL,
  data_criacao DATE NOT NULL,
  data_atualizacao DATE,
  pr_id INT NOT NULL,
  us_id INT NOT NULL,
  FOREIGN KEY(pr_id) REFERENCES processo(id),
  FOREIGN KEY(us_id) REFERENCES usuario(id)
);