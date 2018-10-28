package br.com.softplan.processos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softplan.processos.dao.PermissaoDAO;
import br.com.softplan.processos.model.Permissao;

@Service
public class ServicoPermissaoImpl implements ServicoPermissao {

    @Autowired
    private PermissaoDAO permissaoDAO;

    @Override
    public List<Permissao> selecionarTodas() {
	return permissaoDAO.findAll();
    }

}
