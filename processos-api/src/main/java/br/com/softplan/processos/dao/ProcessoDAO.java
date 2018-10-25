package br.com.softplan.processos.dao;

import org.springframework.data.repository.CrudRepository;

import br.com.softplan.processos.model.Processo;

public interface ProcessoDAO extends CrudRepository<Processo, Long> {

}
