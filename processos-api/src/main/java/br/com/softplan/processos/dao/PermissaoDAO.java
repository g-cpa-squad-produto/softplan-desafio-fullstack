package br.com.softplan.processos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.softplan.processos.model.Permissao;

public interface PermissaoDAO extends JpaRepository<Permissao, String> {

}
