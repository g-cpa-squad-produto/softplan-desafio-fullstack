package org.gradle.interfacedao;

import org.gradle.entidade.Processo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfaceProcessoDAO extends JpaRepository<Processo, Long> {

}
