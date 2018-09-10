package br.com.softplan.dao;

import br.com.softplan.domain.Processousuario;

import java.util.List;

public interface ProcessousuarioDao {
    void salvar(Processousuario processousuario);

    List<Processousuario> recuperarPorProcesso(long processoId);

    Processousuario recuperarPorProcessoIdEUsuarioId(long processoId, long usuarioId);

    void atualizar(Processousuario processousuario);

    void excluir(long processousuarioId);
}
