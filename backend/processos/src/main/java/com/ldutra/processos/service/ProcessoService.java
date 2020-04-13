package com.ldutra.processos.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.ldutra.processos.model.entity.Processo;
import com.ldutra.processos.model.enums.StatusProcesso;

public interface ProcessoService {

	Processo salvar(Processo processo);
	
	Processo atualizar(Processo processo);
	
	void deletar(Processo processo);
	
	List<Processo> buscar( Processo processoFiltro );
	
	void atualizarStatus(Processo processo, StatusProcesso status);
	
	void validar(Processo processo);
	
	Optional<Processo> obterPorId(Long id);
	
}
