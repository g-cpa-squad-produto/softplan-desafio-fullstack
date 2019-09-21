package br.com.processos.processo.implementation;

import br.com.processos.processo.implementation.usecase.VerificarUsuarioRelacionadoComProcessos;
import br.com.processos.processo.specification.IProcessoUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProcessoUsuarioImpl implements IProcessoUsuario {

    @Autowired
    private VerificarUsuarioRelacionadoComProcessos verificarUsuarioRelacionadoComProcessos;

    @Override
    public Boolean verificarUsuarioRelacionadoProcessos(Long usuarioId) {
        return verificarUsuarioRelacionadoComProcessos.executar(usuarioId);
    }
}
