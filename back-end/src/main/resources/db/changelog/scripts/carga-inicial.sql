insert into softplan.tb_papel(pp_id, pp_descricao) values (nextval('softplan.seq_papel'), 'Administrador');
insert into softplan.tb_papel(pp_id, pp_descricao) values (nextval('softplan.seq_papel'), 'Usuário-Triador');
insert into softplan.tb_papel(pp_id, pp_descricao) values (nextval('softplan.seq_papel'), 'Usuário-Finalizador');

insert into softplan.tb_usuario(us_id, us_email, us_nome, us_senha, us_situacao, pp_id) values (nextval('softplan.seq_usuario'), 'administrador@teste.com', 'Administrador', '$2a$10$EblZqNptyYvcLm/VwDCVAuBjzZOI7khzdyGPBr08PpIi0na624b8.', 'ATIVO', (select pp_id from softplan.tb_papel where pp_descricao = 'Administrador'));

insert into softplan.tb_permissao(pe_id, pe_nome, pe_descricao) values (nextval('softplan.seq_permissao'), 'Usuario.Criar', 'Permite criar usuário');
insert into softplan.tb_permissao(pe_id, pe_nome, pe_descricao) values (nextval('softplan.seq_permissao'), 'Usuario.Editar', 'Permite editar usuário');
insert into softplan.tb_permissao(pe_id, pe_nome, pe_descricao) values (nextval('softplan.seq_permissao'), 'Usuario.Visualizar', 'Permite visualizar usuário');

insert into softplan.tb_permissao(pe_id, pe_nome, pe_descricao) values (nextval('softplan.seq_permissao'), 'Processo.Visualizar', 'Permite visualizar processo');
insert into softplan.tb_permissao(pe_id, pe_nome, pe_descricao) values (nextval('softplan.seq_permissao'), 'Processo.Criar', 'Permite criar processo');
insert into softplan.tb_permissao(pe_id, pe_nome, pe_descricao) values (nextval('softplan.seq_permissao'), 'Processo.DelegarParecer', 'Permite visualizar pareceres e incluir usuário para realizar parecer');
insert into softplan.tb_permissao(pe_id, pe_nome, pe_descricao) values (nextval('softplan.seq_permissao'), 'Processo.IncluirParecer', 'Permite incluir parecer a um processo');


insert into softplan.tb_permissao_papel(pe_id, pp_id) values ((select pe_id from softplan.tb_permissao where pe_nome = 'Usuario.Criar'), (select pp_id from softplan.tb_papel where pp_descricao = 'Administrador'));
insert into softplan.tb_permissao_papel(pe_id, pp_id) values ((select pe_id from softplan.tb_permissao where pe_nome = 'Usuario.Editar'), (select pp_id from softplan.tb_papel where pp_descricao = 'Administrador'));
insert into softplan.tb_permissao_papel(pe_id, pp_id) values ((select pe_id from softplan.tb_permissao where pe_nome = 'Usuario.Visualizar'), (select pp_id from softplan.tb_papel where pp_descricao = 'Administrador'));

insert into softplan.tb_permissao_papel(pe_id, pp_id) values ((select pe_id from softplan.tb_permissao where pe_nome = 'Processo.Visualizar'), (select pp_id from softplan.tb_papel where pp_descricao = 'Usuário-Triador'));
insert into softplan.tb_permissao_papel(pe_id, pp_id) values ((select pe_id from softplan.tb_permissao where pe_nome = 'Processo.Criar'), (select pp_id from softplan.tb_papel where pp_descricao = 'Usuário-Triador'));
insert into softplan.tb_permissao_papel(pe_id, pp_id) values ((select pe_id from softplan.tb_permissao where pe_nome = 'Processo.DelegarParecer'), (select pp_id from softplan.tb_papel where pp_descricao = 'Usuário-Triador'));

insert into softplan.tb_permissao_papel(pe_id, pp_id) values ((select pe_id from softplan.tb_permissao where pe_nome = 'Processo.Visualizar'), (select pp_id from softplan.tb_papel where pp_descricao = 'Usuário-Finalizador'));
insert into softplan.tb_permissao_papel(pe_id, pp_id) values ((select pe_id from softplan.tb_permissao where pe_nome = 'Processo.IncluirParecer'), (select pp_id from softplan.tb_papel where pp_descricao = 'Usuário-Finalizador'));

