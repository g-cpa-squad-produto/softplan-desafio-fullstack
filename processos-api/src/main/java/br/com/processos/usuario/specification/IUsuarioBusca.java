package br.com.processos.usuario.specification;

import br.com.processos.usuario.specification.entity.Usuario;

import java.util.List;

public interface IUsuarioBusca {

    Usuario buscarPorId(Long id);

    List<Usuario> buscarPorIdIn(List<Long> ids);

    List<Usuario> buscarTodos();

}
