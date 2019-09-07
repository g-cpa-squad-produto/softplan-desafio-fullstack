insert into softplan.tb_papel(pp_id, pp_descricao) values (nextval('softplan.seq_papel'), 'Administrador');
insert into softplan.tb_papel(pp_id, pp_descricao) values (nextval('softplan.seq_papel'), 'Usuário-Triador');
insert into softplan.tb_papel(pp_id, pp_descricao) values (nextval('softplan.seq_papel'), 'Usuário-Finalizador');

insert into softplan.tb_usuario(us_id, us_email, us_nome, us_senha, pp_id) values (nextval('softplan.seq_usuario'), 'administrador@teste.com', 'Administrador', '$2a$10$EblZqNptyYvcLm/VwDCVAuBjzZOI7khzdyGPBr08PpIi0na624b8.', (select pp_id from softplan.tb_papel where pp_descricao = 'Administrador'));

insert into softplan.tb_permissao(pe_id, pe_nome, pe_descricao) values (nextval('softplan.seq_permissao'), 'Usuario.Criar', 'Permite criar usuário');

insert into softplan.tb_permissao_papel(pe_id, pp_id) values ((select pe_id from softplan.tb_permissao where pe_nome = 'Usuario.Criar'), (select pp_id from softplan.tb_papel where pp_descricao = 'Administrador'));
