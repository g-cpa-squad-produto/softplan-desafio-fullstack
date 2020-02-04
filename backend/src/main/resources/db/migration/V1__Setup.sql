
    create table tb_parecer (
       data_criacao timestamp,
        situacao varchar(30),
        texto varchar(255),
        processo_id int8 not null,
        criado_por int8,
        primary key (processo_id)
    );

    create table tb_processo (
       id  bigserial not null,
        data_criacao timestamp,
        descricao varchar(255),
        titulo varchar(100),
        criado_por int8,
        primary key (id)
    );

    create table tb_processo_usuario (
       data_criacao timestamp,
        processo_id int8 not null,
        usuario_id int8 not null,
        criado_por int8,
        primary key (processo_id, usuario_id)
    );

    create table tb_usuario (
       id  bigserial not null,
        email varchar(100) not null,
        senha varchar(100) not null,
        perfil varchar(50),
        primeiro_nome varchar(100) not null,
        sobrenome varchar(100) not null,
        primary key (id)
    );

    alter table tb_processo 
       add constraint UK_nm60mf6sig1t4div709qdulla unique (titulo);

    alter table tb_usuario 
       add constraint UK_spmnyb4dsul95fjmr5kmdmvub unique (email);

    alter table tb_parecer 
       add constraint FKrgtc5bpw00gb9qjto48haapua 
       foreign key (criado_por) 
       references tb_usuario;

    alter table tb_parecer 
       add constraint FKt1f57qx63oun7j97oduvpdnky 
       foreign key (processo_id) 
       references tb_processo;

    alter table tb_processo 
       add constraint FKf20dg1wta0igq6iyjs4f4gjgv 
       foreign key (criado_por) 
       references tb_usuario;

    alter table tb_processo_usuario 
       add constraint FK6j7scjx6hl0diiugw3dhyyye 
       foreign key (processo_id) 
       references tb_processo;

    alter table tb_processo_usuario 
       add constraint FKc7ce8yyq35j2yvt6t87rafgxk 
       foreign key (usuario_id) 
       references tb_usuario;

    alter table tb_processo_usuario 
       add constraint FKhujmcmg60st4ebosfoghi85yh 
       foreign key (criado_por) 
       references tb_usuario;
