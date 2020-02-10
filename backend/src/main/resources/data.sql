INSERT INTO
USER
    (ID, LOGIN, PASSWORD, FIRST_NAME, LAST_NAME, EMAIL, ROLE)
VALUES
    (1,'admin',    '$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC', 'Antonio', 'da Silva', 'antonio@tst.com.br', 'ADM'),
    (2,'triador1', '$2a$10$j/kCNrlj79fMKpibg1CYMeBkO9LLv.LjSJfwo779Gz1K5LabuOjVa', 'Tiago', 'Souza',      'tiago@tst.com.br', 'TRIADOR'),
    (3,'triador2', '$2a$10$NSJhKkVocL2DJb.rsnDbQe.WlWx.080RGFakPQMGjlFGH0lMU43Jq', 'Tulio', 'Maia',       'tulio@tst.com.br', 'TRIADOR'),
    (4,'triador3', '$2a$10$qtlKrQ9F3gs.U5hCD3tiX.GA9BbzUmyI0iqBXlsKsayXR6mVwqXX.', 'Tais',  'Augusto',    'tais@tst.com.br',  'TRIADOR'),
    (5,'finalizador1', 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJmaW5hbGl6YWRvcjEiLCJhdXRoIjoiIiwiZXhwIjoxNTg3MzMyODg1fQ.0HHivb5tTbSawkbHuFy2prb3TQ96B6tc15eS2X-LIGdgGap2mM1-elDBbC-Td_k88lYiyY6Jbd_q7-rfu9dXrw', 'Fabio',    'Souza',   'fabio@tst.com.br',    'FINALIZADOR'),
    (6,'finalizador2', 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJmaW5hbGl6YWRvcjIiLCJhdXRoIjoiIiwiZXhwIjoxNTg3MzMyOTAxfQ.ukD5-KO_-Kj6FyygLgizTYpsVKzdX_sdqRAxAyf24PRYD_PHM5tPtNLodZe-mdZEb90KLPKTSJgMPJT7f7iU9w', 'Fabiana',  'Augusto', 'fabiana@tst.com.br',  'FINALIZADOR'),
    (7,'finalizador3', 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJmaW5hbGl6YWRvcjMiLCJhdXRoIjoiIiwiZXhwIjoxNTg3MzMyOTM4fQ.h-HBwYtdu7CoZfofapcMs2CrFUrcR8-8tG0D3n9eowPGNjp1YwGBBczlt4Bx6wzQCBAJo6le9iJJIae94gflTQ', 'Fernanda', 'Costa',   'fernanda@tst.com.br', 'FINALIZADOR'),
    (8,'finalizador4', 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJmaW5hbGl6YWRvcjQiLCJhdXRoIjoiIiwiZXhwIjoxNTg3MzMyOTQ0fQ.D6UEbX3XLzTd-2PXf64xPxGPQph_hbyGcvn4jIw5H-RN2njj3d9A99c1qwu1PSjmUzq6wb-_VOtb0GD5z-uYkA', 'Felipe',   'Silva',   'felipe@tst.com.br',   'FINALIZADOR'),
    (9,'finalizador5', 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJmaW5hbGl6YWRvcjUiLCJhdXRoIjoiIiwiZXhwIjoxNTg3MzMyOTUwfQ.8GiaJiDYsmrZi9XdeHHcePsF90XTbBk1nQUu_fxltwGO29KMBx-77xa-NUeQSojdfcgqU0nVYYEXYpLDJDQoEw', 'Fonseca',  'Rosa',    'fonseca@tst.com.br',  'FINALIZADOR');

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
