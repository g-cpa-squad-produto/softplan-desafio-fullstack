package br.com.processos.usuario.specification;

import br.com.processos.usuario.specification.entity.Usuario;

public interface IUsuarioPersistencia {

    Usuario inserir(Usuario novoUsuario);

    Usuario atualizar(Long id, Usuario usuarioModificado);

    void remover(Long id);
}
