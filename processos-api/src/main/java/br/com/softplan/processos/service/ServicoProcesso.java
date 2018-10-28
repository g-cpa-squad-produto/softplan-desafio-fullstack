package br.com.softplan.processos.service;

import java.util.List;

import br.com.softplan.processos.exception.GenericException;
import br.com.softplan.processos.model.Processo;

public interface ServicoProcesso {

    /**
     * Retorna a lista de todos os processos
     * 
     * @return List<Processo>
     * @throws GenericException
     */
    public List<Processo> selecionarTodos() throws GenericException;

    /**
     * Retorna o processo de acordo com o ID
     * 
     * @param id
     * @return Processo
     * @throws GenericException
     */
    public Processo selecionarProcessoPorId(Long id) throws GenericException;

    /**
     * Adiciona um novo processo
     * 
     * @param processo
     * @throws GenericException
     */
    public Processo adicionarProcesso(Processo processo) throws GenericException;
}
