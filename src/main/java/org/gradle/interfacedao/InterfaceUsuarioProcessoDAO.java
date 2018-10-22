package org.gradle.interfacedao;

import org.gradle.entidade.UsuarioProcesso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfaceUsuarioProcessoDAO extends JpaRepository<UsuarioProcesso, Long> {

}
