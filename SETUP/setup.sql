insert into role (nome_role) values ('ROLE_ADMIN');
insert into role (nome_role) values ('ROLE_USER');
insert into role (nome_role) values ('ROLE_TRIADOR'); 
insert into role (nome_role) values ('ROLE_FINALIZADOR');

--SENHA DO USUÁRIO É : processos

insert into usuario (nome,login,senha,email,data_cadastro) 
values('Luiz Jalvir da Silva','luiz','$2a$10$Q1IlvQwEnm7kPR0In4zxz.zOG9.Gs5fSnuo4U7tuctzPhdr7sxVIm','luizjalvir.silva@gmail.com',now());
insert into usuario_roles (usuario,role) values ('luiz','ROLE_USER');
insert into usuario_roles (usuario,role) values ('luiz','ROLE_ADMIN');

