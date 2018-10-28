package br.com.softplan.processos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softplan.processos.dao.ProcessoDAO;
import br.com.softplan.processos.exception.GenericException;
import br.com.softplan.processos.model.Processo;

@Service
public class ServicoProcessoImpl implements ServicoProcesso {

    @Autowired
    private ProcessoDAO processoDAO;

    @Override
    public List<Processo> selecionarTodos() throws GenericException {
	try {
	    return processoDAO.findAll();
	} catch (Exception e) {
	    throw new GenericException("Falha ao carregar os processos");
	}
    }

    @Override
    public Processo selecionarProcessoPorId(Long id) throws GenericException {
	try {
	    return processoDAO.findOne(id);
	} catch (Exception e) {
	    throw new GenericException("Falha ao ler informações do processo");
	}
    }

    @Override
    public Processo adicionarProcesso(Processo processo) throws GenericException {
	try {
	    return processoDAO.save(processo);
	} catch (Exception e) {
	    throw new GenericException("Falha ao tentar cadastrar o processo");
	}
    }

}
