--liquibase formatted sql

--changeset marcos.leon:010

INSERT INTO PUBLIC.USUARIOS (id, nome, email, login_acesso, senha, perfil) VALUES (NEXTVAL('public.seq_usuarios'), 'Usuário administrador', 'admin@dominio.com', 'admin', 'B03FA9D785A4AEB962F3F143C9F6E1C51D004C42B8DE0952D75FBB742C361FBB', 'A');
INSERT INTO PUBLIC.USUARIOS (id, nome, email, login_acesso, senha, perfil) VALUES (NEXTVAL('public.seq_usuarios'), 'Usuário triador', 'triador@dominio.com', 'triador', 'B03FA9D785A4AEB962F3F143C9F6E1C51D004C42B8DE0952D75FBB742C361FBB', 'T');
INSERT INTO PUBLIC.USUARIOS (id, nome, email, login_acesso, senha, perfil) VALUES (NEXTVAL('public.seq_usuarios'), 'Usuário finalizador', 'finalizador@dominio.com', 'finalizador', 'B03FA9D785A4AEB962F3F143C9F6E1C51D004C42B8DE0952D75FBB742C361FBB', 'F');

INSERT INTO PUBLIC.PROCESSOS (id, sumula, dh_abertura, id_usuario_abertura) VALUES (NEXTVAL('public.seq_processos'), 'Processo A', current_timestamp, 2);
INSERT INTO PUBLIC.PROCESSOS (id, sumula, dh_abertura, id_usuario_abertura) VALUES (NEXTVAL('public.seq_processos'), 'Processo B', current_timestamp, 2);
INSERT INTO PUBLIC.PROCESSOS (id, sumula, dh_abertura, id_usuario_abertura) VALUES (NEXTVAL('public.seq_processos'), 'Processo C', current_timestamp, 2);
INSERT INTO PUBLIC.PROCESSOS (id, sumula, dh_abertura, id_usuario_abertura) VALUES (NEXTVAL('public.seq_processos'), 'Processo D', current_timestamp, 2);
INSERT INTO PUBLIC.PROCESSOS (id, sumula, dh_abertura, id_usuario_abertura) VALUES (NEXTVAL('public.seq_processos'), 'Processo E', current_timestamp, 2);
INSERT INTO PUBLIC.PROCESSOS (id, sumula, dh_abertura, id_usuario_abertura) VALUES (NEXTVAL('public.seq_processos'), 'Processo F', current_timestamp, 2);

INSERT INTO PUBLIC.ATRIBUICOES (id, id_processo, id_usuario_triador, id_usuario_finalizador, dh_atribuicao) VALUES (NEXTVAL('public.seq_atribuicoes'), 1, 2, 3, current_timestamp);
INSERT INTO PUBLIC.ATRIBUICOES (id, id_processo, id_usuario_triador, id_usuario_finalizador, dh_atribuicao) VALUES (NEXTVAL('public.seq_atribuicoes'), 2, 2, 3, current_timestamp);
INSERT INTO PUBLIC.ATRIBUICOES (id, id_processo, id_usuario_triador, id_usuario_finalizador, dh_atribuicao) VALUES (NEXTVAL('public.seq_atribuicoes'), 3, 2, 3, current_timestamp);
INSERT INTO PUBLIC.ATRIBUICOES (id, id_processo, id_usuario_triador, id_usuario_finalizador, dh_atribuicao) VALUES (NEXTVAL('public.seq_atribuicoes'), 4, 2, 3, current_timestamp);
INSERT INTO PUBLIC.ATRIBUICOES (id, id_processo, id_usuario_triador, id_usuario_finalizador, dh_atribuicao) VALUES (NEXTVAL('public.seq_atribuicoes'), 5, 2, 3, current_timestamp);
INSERT INTO PUBLIC.ATRIBUICOES (id, id_processo, id_usuario_triador, id_usuario_finalizador, dh_atribuicao) VALUES (NEXTVAL('public.seq_atribuicoes'), 6, 2, 3, current_timestamp);

INSERT INTO PUBLIC.PARECERES (id, id_processo, id_atribuicao, id_usuario_parecer, texto_parecer, dh_parecer) VALUES (NEXTVAL('public.seq_pareceres'), 1, 1, 3, 'Deferido conforme parecer', current_timestamp);
INSERT INTO PUBLIC.PARECERES (id, id_processo, id_atribuicao, id_usuario_parecer, texto_parecer, dh_parecer) VALUES (NEXTVAL('public.seq_pareceres'), 2, 2, 3, 'Ineferido conforme parecer', current_timestamp);

COMMIT;

--rollback ROLLBACK;