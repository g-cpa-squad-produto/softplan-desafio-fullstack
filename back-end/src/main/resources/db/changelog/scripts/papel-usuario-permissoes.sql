-------------- Papel --------------

create sequence softplan.seq_papel
 increment by 1
 start with 1
 no maxvalue
 no minvalue
 cache 1
;
create table softplan.tb_papel(
 pp_id integer NOT NULL,
 pp_descricao character varying(100),
 constraint pk_pp_id primary key (pp_id)
);

-------------- Permissão --------------

create sequence softplan.seq_permissao
 increment by 1
 start with 1
 no maxvalue
 no minvalue
;

create table softplan.tb_permissao
(
 pe_id integer not null,
 pe_nome character varying(255) not null,
 pe_descricao character varying(255)null,
 constraint pk_pe_id primary key (pe_id)
);


-------------- Permissão Papel --------------

create table softplan.tb_permissao_papel
(
 pe_id integer not null,
 pp_id integer not null
);

alter table softplan.tb_permissao_papel add constraint fk_pp_papel foreign key (pp_id) references softplan.tb_papel (pp_id) on update no action on delete no action;
;
alter table softplan.tb_permissao_papel add constraint fk_pp_permissao foreign key (pe_id) references softplan.tb_permissao (pe_id) on update no action on delete no action;
;

-------------- Usuário --------------

create sequence softplan.seq_usuario
 increment by 1
 start with 1
 no maxvalue
 no minvalue
 cache 1
;


create table softplan.tb_usuario(
 us_id integer not null,
 us_email character varying(64) not null,
 us_nome character varying(64) not null,
 us_senha character varying(64) not null,
 pp_id integer not null,
 constraint pk_us_id PRIMARY KEY (us_id)
);

alter table softplan.tb_usuario add constraint fk_usuario_papel foreign key (pp_id) references softplan.tb_papel (pp_id) on update no action on delete no action;

