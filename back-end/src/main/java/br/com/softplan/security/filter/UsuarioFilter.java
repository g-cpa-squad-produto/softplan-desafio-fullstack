package br.com.softplan.security.filter;

import br.com.softplan.security.entity.QUsuario;
import br.com.softplan.utils.QueryDslFilter;

public class UsuarioFilter extends QueryDslFilter {

    public void setNome(String nome){
        addToMainBooleanExpression(QUsuario.usuario.nome.containsIgnoreCase(nome));
    }

}
