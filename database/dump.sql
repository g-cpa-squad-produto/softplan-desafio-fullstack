
-- public.processes definition

-- Drop table

-- DROP TABLE public.processes;

CREATE TABLE public.processes (
	process_id serial NOT NULL,
	"number" varchar(255) NULL,
	report varchar(255) NULL,
	CONSTRAINT processes_pkey PRIMARY KEY (process_id)
);


-- public.users definition

-- Drop table

-- DROP TABLE public.users;

CREATE TABLE public.users (
	user_id serial NOT NULL,
	email varchar(255) NULL,
	"name" varchar(255) NULL,
	"password" varchar(255) NULL,
	roles varchar(255) NULL,
	CONSTRAINT users_pkey PRIMARY KEY (user_id)
);


-- public.user_processes definition

-- Drop table

-- DROP TABLE public.user_processes;

CREATE TABLE public.user_processes (
	user_id int4 NOT NULL,
	process_id int4 NOT NULL,
	CONSTRAINT user_processes_pkey PRIMARY KEY (process_id, user_id),
	CONSTRAINT fkixqjyrf1x8gterf2thieu7gau FOREIGN KEY (process_id) REFERENCES processes(process_id),
	CONSTRAINT fkqog4sbq5o1pqyf56wkaohl8ym FOREIGN KEY (user_id) REFERENCES users(user_id)
);



INSERT INTO public.users (user_id,email,"name","password",roles) VALUES 
(8,'barbaracbgaribaldi@gmail.com','Bárbara','123456','ADMIN')
,(9,'ingrid@unpg.com.br','Ingrid','123456','FINALIZADOR')
,(10,'tiago@unpg.com.br','Tiago','123456','TRIADOR')
,(11,'manu@ungp.com.br','Manu','123456','FINALIZADOR')
;

INSERT INTO public.processes (process_id,"number",report) VALUES 
(1,'11233','')
,(2,'122331','')
;

INSERT INTO public.user_processes (user_id,process_id) VALUES 
(9,1)
,(11,2)
;
