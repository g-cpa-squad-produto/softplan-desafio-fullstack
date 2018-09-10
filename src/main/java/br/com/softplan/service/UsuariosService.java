package br.com.softplan.service;

import br.com.softplan.domain.Usuarios;

import java.util.List;

public interface UsuariosService {

    void salvar(Usuarios usuario);

    List<Usuarios> recuperar();

    Usuarios recuperarPorId(long id);

    void atualizar(Usuarios usuario);

    void excluir(long id);

    Usuarios recuperarPorUsername(String username);

}
