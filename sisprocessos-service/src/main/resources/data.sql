-- Usuários
insert into usuario (id, nome, cpf, telefone, aniversario, endereco, ativo, login, senha, perfil) values
(1, 'Samuel', '111.111.111-11', '6199999-9024', '1992-02-11', 'QN 502', true, 'samuel', '12345', 'ADMINISTRADOR'),
(2, 'Daiely', '222.222.222-22', '6122222-2222', '1991-04-22', 'QN 502', true, 'daiely', '12345', 'TRIADOR'),
(3, 'Miguel', '333.333.333-33', '6133333-3333', '2012-12-13', 'QN 502', true, 'miguel', '12345', 'FINALIZADOR'),
(4, 'Sophia', '444.444.444-44', '6144444-4444', '2019-01-01', 'QN 502', false, 'sophia', '12345', 'FINALIZADOR'),
(5, 'Joanida', '555.555.555-55', '6155555-5555', '1969-01-22', 'Barro Alto', true, 'joanida', '12345', 'TRIADOR'),
(6, 'José', '666.666.666-66', '6166666-6666', '1965-05-06', 'Barro Alto', false, 'jose', '12345', 'FINALIZADOR'),
(7, 'Bárbara', '777.777.777-77', '6177777-7777', '1989-10-16', 'QNO', true, 'barbara', '12345', 'FINALIZADOR'),
(8, 'Esnilto', '888.888.888-88', '6188888-8888', '1973-07-15', 'QNO', false, 'esnilto', '12345', 'FINALIZADOR');

-- Processos
insert into processo (id, numero, data, descricao, parecer, id_usuario_parecer, status) values
(1, 1001, '2018-01-01', 'Processo 1001', null, null, 'NOVO'),
(2, 2001, '2018-01-15', 'Processo 2001', null, null, 'NOVO'),
(3, 3001, '2018-01-17', 'Processo 3001', null, null, 'NOVO'),
(4, 4001, '2018-01-22', 'Processo 4001', null, null, 'NOVO'),
(5, 5001, '2018-01-28', 'Processo 5001', null, null, 'NOVO');