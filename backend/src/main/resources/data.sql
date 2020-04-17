insert into user
  (id, username, password, name, last_name, role)
values
  (1, 'admin', '$2a$10$YaygI7/KGqjMsYgBfzdwXOOiNtSZIZsWrqYV9hn0gvN1slqrvi3t6', 'Administrador', 'Principal', 'ADMIN'),
  (2, 'triador', '$2a$10$0gFAVNSyfqGxYxNWze88sOIfeHNtf0OlxvxOknN8GIu5qGpcGI5xe', 'Triador', 'Principal', 'TRIADOR'),
  (3, 'finalizador1', '$2a$10$8rE4fbI6.QpDo67FRFLXC.6KNsJ5uPcw6GsKkAO2L5T.LYVYdMOMi', 'Finalizador', 'Primeiro', 'FINALIZADOR'),
  (4, 'finalizador2', '$2a$10$RLoE2ud9.j5U5vr3y2tUxOL0nbG9B0GI.ypEJUBItYr6sfkGPsNCK', 'Finalizador', 'Segundo', 'FINALIZADOR');

insert into process
  (id, title, description, status)
values
  (1, 'Processo numero 1', 'descrição 1', 'PENDING'),
  (2, 'Processo numero 2', 'descrição 2', 'PENDING'),
  (3, 'Processo numero 3', 'descrição 3', 'PENDING'),
  (4, 'Processo numero 4', 'descrição 4', 'PENDING');

