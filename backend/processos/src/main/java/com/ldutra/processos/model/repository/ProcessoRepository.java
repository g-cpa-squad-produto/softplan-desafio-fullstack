package com.ldutra.processos.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ldutra.processos.model.entity.Processo;

public interface ProcessoRepository extends JpaRepository<Processo, Long> { 

}
