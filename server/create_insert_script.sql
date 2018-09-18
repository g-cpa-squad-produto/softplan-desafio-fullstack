CREATE TABLE task (
  id            SERIAL PRIMARY KEY,
  name          VARCHAR(100) NOT NULL,
  description  	VARCHAR(100) NULL,
  file_path  	VARCHAR(100) NULL,
  status  		VARCHAR(100) NULL
);

INSERT INTO task (id, name, description, file_path, status)
VALUES (1, 'Create the logo', NULL, 'teste_1_33ba5de4-07f7-4d47-b721-a0c4c5babdf5.png', 'CREATE');

INSERT INTO task (id, name, description, file_path, status)
VALUES ((select max(id) + 1 from task), 'Editing the logo', 'Editing the logo created', 'teste_1_33ba5de4-07f7-4d47-b721-a0c4c5babdf5.png', 'CREATE');

INSERT INTO task (id, name, description, file_path, status)
VALUES ((select max(id) + 1 from task), 'Finish to edit the logo', NULL, 'teste_1_33ba5de4-07f7-4d47-b721-a0c4c5babdf5.png', 'CREATE');