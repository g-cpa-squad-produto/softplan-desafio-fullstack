package br.com.softplan.security.filter;

import br.com.softplan.security.entity.QPermissaoPapel;
import br.com.softplan.security.entity.QUsuario;
import br.com.softplan.security.entity.enumeration.SituacaoUsuarioEnum;
import br.com.softplan.utils.QueryDslFilter;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;

import java.util.Objects;

public class UsuarioFilter extends QueryDslFilter {

    public void setNome(String nome){
        addToMainBooleanExpression(QUsuario.usuario.nome.containsIgnoreCase(nome));
    }

    public void setFinalizadores(String finalizadores){
        if(Objects.nonNull(finalizadores)) {

            JPQLQuery papeisComPermissaoFinalizador= JPAExpressions.select(QPermissaoPapel.permissaoPapel.papel.id).from(QPermissaoPapel.permissaoPapel).where(QPermissaoPapel.permissaoPapel.permissao.nome.eq("Processo.IncluirParecer"));
            addToMainBooleanExpression(QUsuario.usuario.papel.id.in(papeisComPermissaoFinalizador));
        }
    }

    public void somenteAtivos() {
        addToMainBooleanExpression(QUsuario.usuario.situacao.eq(SituacaoUsuarioEnum.ATIVO));
    }
}
