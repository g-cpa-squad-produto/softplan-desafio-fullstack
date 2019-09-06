insert into softplan.tb_papel(pp_id, pp_descricao) values (nextval('softplan.seq_papel'), 'Administrador');
insert into softplan.tb_papel(pp_id, pp_descricao) values (nextval('softplan.seq_papel'), 'Usuário-Triador');
insert into softplan.tb_papel(pp_id, pp_descricao) values (nextval('softplan.seq_papel'), 'Usuário-Finalizador');

insert into softplan.tb_usuario(us_id, us_email, us_nome, us_senha, pp_id) values (nextval('softplan.seq_usuario'), 'administrador@teste.com', 'Administrador', '123546', (select pp_id from softplan.tb_papel where pp_descricao = 'Administrador'));