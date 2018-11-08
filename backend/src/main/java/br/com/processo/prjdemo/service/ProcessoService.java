package br.com.processo.prjdemo.service;

import java.util.List;

import br.com.processo.prjdemo.model.Parecer;
import br.com.processo.prjdemo.model.Processo;

/**
 * 
 * @author Guilherme Sena
 * Interface para os servicos do Processo
 */
public interface ProcessoService {
	Processo getProcessoById(Long id);
	List<Processo> getTodosProcessos();
	Processo salvarProcesso(Processo processo);
	Parecer salvarProcessoParecer(Parecer parecer);
}
