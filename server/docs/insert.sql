CREATE TABLE role (
    id SERIAL PRIMARY KEY,
    name varchar(255),
    code varchar(255)
);

CREATE TABLE "user" (
    id SERIAL PRIMARY KEY,
    name varchar(255),
    email varchar(255),
    password varchar(255),
    role_id INTEGER REFERENCES role(id)
);

CREATE TABLE process (
    id SERIAL PRIMARY KEY,
    name varchar(255),
    code varchar(255),
    seem varchar(255)
);

CREATE TABLE user_process (
    id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES "user"(id), 
    process_id INTEGER REFERENCES process(id)
);

INSERT INTO role (id, name, code)
VALUES(1, 'Administrador', 'adminstrador');

INSERT INTO role (id, name, code)
VALUES(2, 'Usuário - Triador', 'triador');

INSERT INTO role (id, name, code)
VALUES(3, 'Usuário - Finalizador', 'finalizador');

INSERT INTO "user" (id, name, email, password, role_id)
VALUES(0, 'Adminstrador', 'admin@softplan.com', '123', (SELECT id FROM role WHERE code = 'adminstrador'));