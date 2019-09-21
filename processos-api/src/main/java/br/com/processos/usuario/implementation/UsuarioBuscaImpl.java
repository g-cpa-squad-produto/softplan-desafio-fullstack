package br.com.processos.usuario.implementation;

import br.com.processos.usuario.implementation.usecase.busca.BuscarUsuariosPorIdIn;
import br.com.processos.usuario.implementation.usecase.busca.BuscarTodosUsuarios;
import br.com.processos.usuario.implementation.usecase.busca.BuscarUsuarioPorId;
import br.com.processos.usuario.specification.IUsuarioBusca;
import br.com.processos.usuario.specification.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioBuscaImpl implements IUsuarioBusca {

    @Autowired
    private BuscarUsuarioPorId buscarUsuarioPorId;

    @Autowired
    private BuscarTodosUsuarios buscarTodosUsuarios;

    @Autowired
    private BuscarUsuariosPorIdIn buscarUsuariosPorIdIn;

    @Override
    public Usuario buscarPorId(Long id) {
        return buscarUsuarioPorId.executar(id);
    }

    @Override
    public List<Usuario> buscarPorIdIn(List<Long> ids) {
        return buscarUsuariosPorIdIn.executar(ids);
    }

    @Override
    public List<Usuario> buscarTodos() {
        return buscarTodosUsuarios.executar();
    }

}
