package com.teste.magnum.gerenciadorprocessosapi.repository;

import com.teste.magnum.gerenciadorprocessosapi.domain.Parecer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParecerRepository extends JpaRepository<Parecer, Long> {
}
