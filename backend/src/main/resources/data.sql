INSERT INTO
USER
    (ID, LOGIN, PASSWORD, FIRST_NAME, LAST_NAME, EMAIL, ROLE)
VALUES
    (1,'admin',        '$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC', 'Antonio',  'da Silva', 'antonio@tst.com.br',  'ADM'),
    (2,'triador1',     '$2a$10$j/kCNrlj79fMKpibg1CYMeBkO9LLv.LjSJfwo779Gz1K5LabuOjVa', 'Tiago',    'Souza',    'tiago@tst.com.br',    'TRIADOR'),
    (3,'triador2',     '$2a$10$NSJhKkVocL2DJb.rsnDbQe.WlWx.080RGFakPQMGjlFGH0lMU43Jq', 'Tulio',    'Maia',     'tulio@tst.com.br',    'TRIADOR'),
    (4,'triador3',     '$2a$10$qtlKrQ9F3gs.U5hCD3tiX.GA9BbzUmyI0iqBXlsKsayXR6mVwqXX.', 'Tais',     'Augusto',  'tais@tst.com.br',     'TRIADOR'),
    (5,'finalizador1', '$2a$10$Hie5Mfb4tqLdrWKkuLUYduHrDsEeeiu8GgUnbIlZ1kVFe/TUsvKk.', 'Fabio',    'Souza',    'fabio@tst.com.br',    'FINALIZADOR'),
    (6,'finalizador2', '$2a$10$L23Ta/8UgBYlv44MyUgmNubFPHIvhrAdFE44SsBbYVB6hDXg.5I2m', 'Fabiana',  'Augusto',  'fabiana@tst.com.br',  'FINALIZADOR'),
    (7,'finalizador3', '$2a$10$Sjpufc0eMbS1t3v2xSsNOulqW0yi1vvfuBrT1k8QVA983B4bOTije', 'Fernanda', 'Costa',    'fernanda@tst.com.br', 'FINALIZADOR'),
    (8,'finalizador4', '$2a$10$xzfZLFp6CCOhgwrPjg4Rn.l/0nqij9SBqDjiBYJs1GhiqhMvAvSse', 'Felipe',   'Silva',    'felipe@tst.com.br',   'FINALIZADOR'),
    (9,'finalizador5', '$2a$10$ZuelIRZMCx0.hWyzyhUSm.Uc.4Fvq2DSacuEp/7Zc0UaEM3qGmjaS', 'Fonseca',  'Rosa',     'fonseca@tst.com.br',  'FINALIZADOR');

INSERT INTO
PROCESS
    (ID, TITLE, DESCRIPTION, AUTOR_ID)
VALUES
    (1, 'Solicitação de Avaliação', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry.', '2'),
    (2, 'Teste de Avaliação',       'Lorem Ipsum is simply dummy text of the printing and typesetting industry.', '2'),
    (3, 'Teste de Usuário',         'Lorem Ipsum is simply dummy text of the printing and typesetting industry.', '3');

INSERT INTO
REPORT
    (ID, DESCRIPTION, STATUS, AUTOR_ID, PROCESS_ID)
VALUES
    (1, 'De acordo', 'CONCLUIDO', 5, 1),
    (2, NULL, 'PENDENTE', 6, 1),
    (3, NULL, 'PENDENTE', 7, 1),

    (4, 'De acordo', 'CONCLUIDO', 8, 2),
    (5, NULL, 'PENDENTE', 9, 2),
    (6, NULL, 'PENDENTE', 9, 3);
