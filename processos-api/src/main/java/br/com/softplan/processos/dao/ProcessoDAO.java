package br.com.softplan.processos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.softplan.processos.model.Processo;

public interface ProcessoDAO extends JpaRepository<Processo, Long> {

}
