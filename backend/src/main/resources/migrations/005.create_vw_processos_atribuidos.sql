--liquibase formatted sql

--changeset marcos.leon:005

CREATE OR REPLACE VIEW PUBLIC.VW_PROCESSOS_ATRIBUIDOS AS
SELECT atr.id as id_atribuicao, pro.id as id_processo, pro.sumula, pro.id_usuario_abertura, pro.dh_abertura, atr.id_usuario_triador, atr.dh_atribuicao, atr.id_usuario_finalizador,
       CASE (SELECT COUNT(*) FROM public.pareceres par WHERE atr.id = par.id_atribuicao) WHEN 0 THEN 'N' ELSE 'S' END AS POSSUI_PARECER
FROM public.processos pro JOIN
     public.atribuicoes atr ON (pro.id = atr.id_processo)

--rollback DROP VIEW PUBLIC.VW_PROCESSOS_ATRIBUIDOS;