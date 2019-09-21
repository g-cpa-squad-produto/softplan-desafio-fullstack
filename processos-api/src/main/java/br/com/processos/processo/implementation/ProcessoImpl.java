package br.com.processos.processo.implementation;

import br.com.processos.processo.implementation.usecase.BuscarProcessoPorId;
import br.com.processos.processo.implementation.usecase.BuscarProcessosPendentesParecer;
import br.com.processos.processo.implementation.usecase.BuscarTodosProcessos;
import br.com.processos.processo.implementation.usecase.InserirProcesso;
import br.com.processos.processo.implementation.usecase.RealizarParecer;
import br.com.processos.processo.implementation.usecase.SolicitarParecer;
import br.com.processos.processo.specification.IProcesso;
import br.com.processos.processo.specification.entity.Parecer;
import br.com.processos.processo.specification.entity.Processo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProcessoImpl implements IProcesso {

    @Autowired
    private BuscarProcessoPorId buscarProcessoPorId;

    @Autowired
    private BuscarTodosProcessos buscarTodosProcessos;

    @Autowired
    private BuscarProcessosPendentesParecer buscarProcessosPendentesParecer;

    @Autowired
    private InserirProcesso inserirProcesso;

    @Autowired
    private SolicitarParecer solicitarParecer;

    @Autowired
    private RealizarParecer realizarParecer;

    @Override
    public Processo buscarProcessoPorId(Long processoId) {
        return buscarProcessoPorId.executar(processoId);
    }

    @Override
    public List<Processo> buscarTodosProcessos() {
        return buscarTodosProcessos.executar();
    }

    @Override
    public List<Processo> buscarProcessosPendentesParecer(Long usuarioId) {
        return buscarProcessosPendentesParecer.executar(usuarioId);
    }

    @Override
    public Processo inserirProcesso(Processo processo) {
        return inserirProcesso.executar(processo);
    }

    @Override
    public List<Parecer> solicitarParecer(Long processoId, List<Long> usuariosId) {
        return solicitarParecer.executar(processoId, usuariosId);
    }

    @Override
    public Parecer realizarParecer(Long parecerId, String textoParecer) {
        return realizarParecer.executar(parecerId, textoParecer);
    }

}
