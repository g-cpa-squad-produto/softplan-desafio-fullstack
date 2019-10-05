package com.isadora.backendapi.repositories;

import com.isadora.backendapi.domain.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository  extends CrudRepository<Usuario, Long> {
    Usuario findByEmail(String email);

}
