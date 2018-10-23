INSERT INTO processos.usuario (codigo, nome, email, senha)
SELECT * FROM (SELECT 1, 'admin', 'admin@admin.com', '123456') AS tmp
WHERE NOT EXISTS (
    SELECT codigo FROM processos.usuario WHERE codigo = 1
) LIMIT 1;

INSERT INTO processos.permissao (nome)
SELECT * FROM (SELECT 'ROLE_ADMIN') AS tmp
WHERE NOT EXISTS (
    SELECT nome FROM processos.permissao WHERE nome = 'ROLE_ADMIN'
) LIMIT 1;

INSERT INTO processos.permissao (nome)
SELECT * FROM (SELECT 'ROLE_USUARIO_TRIADOR') AS tmp
WHERE NOT EXISTS (
    SELECT nome FROM processos.permissao WHERE nome = 'ROLE_USUARIO_TRIADOR'
) LIMIT 1;

INSERT INTO processos.permissao (nome)
SELECT * FROM (SELECT 'ROLE_USUARIO_FINALIZADOR') AS tmp
WHERE NOT EXISTS (
    SELECT nome FROM processos.permissao WHERE nome = 'ROLE_USUARIO_FINALIZADOR'
) LIMIT 1;

INSERT INTO processos.usuario_permissao (codigo_usuario, permissao)
SELECT * FROM (SELECT 1, 'ROLE_ADMIN') AS tmp
WHERE NOT EXISTS (
    SELECT codigo_usuario, permissao FROM processos.usuario_permissao WHERE codigo_usuario = 1 and permissao = 'ROLE_ADMIN'
) LIMIT 1;