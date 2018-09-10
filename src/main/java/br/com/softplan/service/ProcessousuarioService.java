package br.com.softplan.service;

import br.com.softplan.domain.Processousuario;

import java.util.List;

public interface ProcessousuarioService {


    void salvar(Processousuario processousuario, long processoId, long usuarioId);

    List<Processousuario> recuperarPorProcesso(long processoId);

    Processousuario recuperarPorProcessoIdEUsuarioId(long processoId, long usuarioId);

    void atualizar(Processousuario processousuario, long processoId, long usuarioId);

    void excluir(long processoId, long usuarioId);
}
