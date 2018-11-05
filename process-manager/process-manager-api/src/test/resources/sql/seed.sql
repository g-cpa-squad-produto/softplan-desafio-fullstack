Insert into USER_TYPE (user_Type_Id, user_Type_Name) values (1,'ADMINISTRADOR');
Insert into USER_TYPE (user_Type_Id, user_Type_Name) values (2,'USUARIO-TRIADOR');
Insert into USER_TYPE (user_Type_Id, user_Type_Name) values (3,'USUARIO-FINALIZADOR');


Insert into PROCESS_USER (name, password, create_Date, create_By, user_Type_Id) values ('LISCA', 'passtest', to_timestamp('24/10/18 09:02:24,107000000','DD/MM/RR HH24:MI:SSXFF'), 'SystemTest', 1);
Insert into PROCESS_USER (name, password, create_Date, create_By, user_Type_Id) values ('BRESSAN', 'passtest', to_timestamp('24/10/18 09:02:24,107000000','DD/MM/RR HH24:MI:SSXFF'), 'SystemTest', 2);
Insert into PROCESS_USER (name, password, create_Date, create_By, user_Type_Id) values ('BALADA', 'passtest', to_timestamp('24/10/18 09:02:24,107000000','DD/MM/RR HH24:MI:SSXFF'), 'SystemTest', 3);
Insert into PROCESS_USER (name, password, create_Date, create_By, user_Type_Id) values ('JAEL', 'passtest', to_timestamp('24/10/18 09:02:24,107000000','DD/MM/RR HH24:MI:SSXFF'), 'SystemTest', 3);

Insert into PROCESS (PROCESS_NUMBER, CREATE_BY, CREATE_DATE, PROCESS_DESCRIPTION) values (999, 'BRESSAN', to_timestamp('24/10/18 09:02:24,107000000','DD/MM/RR HH24:MI:SSXFF'), 'bla bla bla');
Insert into PROCESS (PROCESS_NUMBER, CREATE_BY, CREATE_DATE, PROCESS_DESCRIPTION, FINISH_DATE) values (800, 'BRESSAN', to_timestamp('24/10/18 09:02:24,107000000','DD/MM/RR HH24:MI:SSXFF'), 'bla bla bla', to_timestamp('24/10/18 09:02:24,107000000','DD/MM/RR HH24:MI:SSXFF'));
Insert into PROCESS (PROCESS_NUMBER, CREATE_BY, CREATE_DATE, PROCESS_DESCRIPTION) values (700, 'BRESSAN', to_timestamp('24/10/18 09:02:24,107000000','DD/MM/RR HH24:MI:SSXFF'), 'other bla bla bla');

Insert into PROCESS_AUTHORIZED_USERS (PROCESS_PROCESS_NUMBER, AUTHORIZED_USERS_NAME) values (999,'BALADA');