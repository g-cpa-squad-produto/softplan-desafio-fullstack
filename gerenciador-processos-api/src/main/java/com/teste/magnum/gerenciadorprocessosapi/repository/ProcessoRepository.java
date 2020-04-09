package com.teste.magnum.gerenciadorprocessosapi.repository;

import com.teste.magnum.gerenciadorprocessosapi.domain.Processo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProcessoRepository extends JpaRepository<Processo, Long> {

    Optional<Processo> getById(Long numero);

}
