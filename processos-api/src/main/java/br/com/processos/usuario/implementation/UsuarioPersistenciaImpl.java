package br.com.processos.usuario.implementation;

import br.com.processos.usuario.implementation.usecase.persistencia.AtualizarUsuario;
import br.com.processos.usuario.implementation.usecase.persistencia.InserirUsuario;
import br.com.processos.usuario.implementation.usecase.persistencia.RemoverUsuario;
import br.com.processos.usuario.specification.IUsuarioPersistencia;
import br.com.processos.usuario.specification.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioPersistenciaImpl implements IUsuarioPersistencia {

    @Autowired
    private InserirUsuario inserirUsuario;

    @Autowired
    private AtualizarUsuario atualizarUsuario;

    @Autowired
    private RemoverUsuario removerUsuario;

    @Override
    public Usuario inserir(Usuario novoUsuarioo) {
        return inserirUsuario.executar(novoUsuarioo);
    }

    @Override
    public Usuario atualizar(Long id, Usuario usuarioModificado) {
        return atualizarUsuario.executar(id, usuarioModificado);
    }

    @Override
    public void remover(Long id) {
        removerUsuario.executar(id);
    }
}
