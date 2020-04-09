package com.teste.magnum.gerenciadorprocessosapi.repository;

import com.teste.magnum.gerenciadorprocessosapi.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> getUsuarioById(Long id);

    Optional<Usuario> getUsuarioByEmailAndSenha(String email, String password);

    Optional<Usuario> getUsuarioByCpf(String cpf);

}
