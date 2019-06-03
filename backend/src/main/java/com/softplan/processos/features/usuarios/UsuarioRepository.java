package com.softplan.processos.features.usuarios;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Long> {

    boolean existsByEmail(String email);

    Usuario findByEmailAndSenha(String email, String senha);

    List<Usuario> findByPerfil(Perfil perfil);

}
