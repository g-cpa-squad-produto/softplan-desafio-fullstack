insert into user
  (id, username, password, name, last_name, role)
values
  (1, 'admin', '$2a$10$YaygI7/KGqjMsYgBfzdwXOOiNtSZIZsWrqYV9hn0gvN1slqrvi3t6', 'name_of_admin', 'lastname_of_admin', 'ADMIN'),
  (2, 'triador', '$2a$10$0gFAVNSyfqGxYxNWze88sOIfeHNtf0OlxvxOknN8GIu5qGpcGI5xe', 'Triagem', 'Da Silva', 'TRIADOR'),
  (3, 'finalizador', '$2a$10$8rE4fbI6.QpDo67FRFLXC.6KNsJ5uPcw6GsKkAO2L5T.LYVYdMOMi', 'Finaliza', 'De Souza', 'FINALIZADOR');

insert into process
  (id, title, description, status)
values
  (1, 'Processo numero 1', 'descrição qualquer', 'PENDING');

