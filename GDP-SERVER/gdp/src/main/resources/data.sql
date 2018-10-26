INSERT INTO USERS (id, login, password, name, last_name, profile ) VALUES (seq_user_id.NEXTVAL, 'yure', '123', 'YURE', 'KESLEY', 'ADMIN');
INSERT INTO USERS (id, login, password, name, last_name, profile ) VALUES (seq_user_id.NEXTVAL, 'admin', '123','ADMIN','ADMIN', 'ADMIN');
INSERT INTO USERS (id, login, password, name, last_name, profile ) VALUES (seq_user_id.NEXTVAL, 'triador', '123','TRIADOR','TRIADOR', 'TRIADOR');
INSERT INTO USERS (id, login, password, name, last_name, profile ) VALUES (seq_user_id.NEXTVAL, 'finalizador','123','FINALIZADOR','FINALIZADOR', 'FINALIZADOR');

INSERT INTO PROCESS (id, name, status  ) VALUES (seq_process_id.NEXTVAL, 'PROCESSO 1', 'CRIADO');
INSERT INTO PROCESS (id, name, status  ) VALUES (seq_process_id.NEXTVAL, 'PROCESSO 2', 'PENDENTE');
INSERT INTO PROCESS (id, name, status  ) VALUES (seq_process_id.NEXTVAL, 'PROCESSO 4', 'FINALIADO');



