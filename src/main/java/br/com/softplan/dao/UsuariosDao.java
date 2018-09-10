package br.com.softplan.dao;

import br.com.softplan.domain.Usuarios;

import java.util.List;

public interface UsuariosDao {

    void salvar(Usuarios usuario);
    List<Usuarios> recuperar();
    Usuarios recuperarPorId(long id);
    void atualizar(Usuarios usuario);
    void excluir(long id);

    Usuarios recuperarPorUsername(String username);
}
