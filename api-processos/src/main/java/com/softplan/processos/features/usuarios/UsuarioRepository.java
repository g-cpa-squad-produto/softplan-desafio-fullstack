package com.softplan.processos.features.usuarios;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Long> {

    Usuario findByLoginAcessoAndSenha(String loginAcesso, String senha);

}
