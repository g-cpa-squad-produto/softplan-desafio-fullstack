package br.com.processos.processo.specification;

import br.com.processos.processo.specification.entity.Parecer;
import br.com.processos.processo.specification.entity.Processo;

import java.util.List;

public interface IProcesso {

    Processo buscarProcessoPorId(Long processoId);

    List<Processo> buscarTodosProcessos();

    List<Processo> buscarProcessosPendentesParecer(Long usuarioId);

    Processo inserirProcesso(Processo processo);

    List<Parecer> solicitarParecer(Long processoId, List<Long> usuariosId);

    Parecer realizarParecer(Long parecerId, String textoParecer);

}
