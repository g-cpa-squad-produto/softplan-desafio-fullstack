-------------- Processo --------------

create sequence softplan.seq_processo
 increment by 1
 start with 1
 no maxvalue
 no minvalue
 cache 1
;
create table softplan.tb_processo(
 pr_id integer NOT NULL,
 pr_numero character varying(100),
 pr_descricao character varying(100),
 constraint pk_pr_id primary key (pr_id)
);

-------------- Parecer Processo --------------

create sequence softplan.seq_parecer_processo
 increment by 1
 start with 1
 no maxvalue
 no minvalue
 cache 1
;
create table softplan.tb_parecer_processo(
 pa_id integer NOT NULL,
 pa_parecer character varying(500),
 pr_id integer NOT NULL,
 us_id integer NOT NULL,
 constraint pk_pa_id primary key (pa_id)
);

alter table softplan.tb_parecer_processo add constraint fk_pa_processo foreign key (pr_id) references softplan.tb_processo (pr_id) on update no action on delete no action;
;
alter table softplan.tb_parecer_processo add constraint fk_pa_usuario foreign key (us_id) references softplan.tb_usuario (us_id) on update no action on delete no action;
;