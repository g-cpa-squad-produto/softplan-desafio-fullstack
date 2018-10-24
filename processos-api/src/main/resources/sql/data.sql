INSERT INTO processosdb.usuario (email, nome, senha)
SELECT * FROM (SELECT 'admin@admin.com', 'admin', '123456') AS tmp
WHERE NOT EXISTS (
    SELECT email FROM processosdb.usuario WHERE email = 'admin@admin.com'
) LIMIT 1;

INSERT INTO processosdb.permissao (nome)
SELECT * FROM (SELECT 'ROLE_ADMIN') AS tmp
WHERE NOT EXISTS (
    SELECT nome FROM processosdb.permissao WHERE nome = 'ROLE_ADMIN'
) LIMIT 1;

INSERT INTO processosdb.permissao (nome)
SELECT * FROM (SELECT 'ROLE_USUARIO_TRIADOR') AS tmp
WHERE NOT EXISTS (
    SELECT nome FROM processosdb.permissao WHERE nome = 'ROLE_USUARIO_TRIADOR'
) LIMIT 1;

INSERT INTO processosdb.permissao (nome)
SELECT * FROM (SELECT 'ROLE_USUARIO_FINALIZADOR') AS tmp
WHERE NOT EXISTS (
    SELECT nome FROM processosdb.permissao WHERE nome = 'ROLE_USUARIO_FINALIZADOR'
) LIMIT 1;

INSERT INTO processosdb.usuario_permissao (email_usuario, permissao)
SELECT * FROM (SELECT 'admin@admin.com', 'ROLE_ADMIN') AS tmp
WHERE NOT EXISTS (
    SELECT email_usuario, permissao FROM processosdb.usuario_permissao WHERE email_usuario = 'admin@admin.com' and permissao = 'ROLE_ADMIN'
) LIMIT 1;