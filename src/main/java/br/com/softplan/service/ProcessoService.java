package br.com.softplan.service;

import br.com.softplan.domain.Processo;

import java.util.List;

public interface ProcessoService {


    void salvar(Processo processo);

    List<Processo> recuperar();

    Processo recuperarPorId(long id);

    void atualizar(Processo processo);

    void excluir(long id);

}
