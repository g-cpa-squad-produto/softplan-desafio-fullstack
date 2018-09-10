package br.com.softplan.service;

import br.com.softplan.dao.UsuariosDao;
import br.com.softplan.domain.Usuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Transactional
public class UsuariosServiceImpl implements UsuariosService{

    @Autowired
    private UsuariosDao usuariosDao;


    @Override
    public void salvar(Usuarios usuario) {
        usuariosDao.salvar(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuarios> recuperar() {
        return usuariosDao.recuperar();
    }

    @Override
    @Transactional(readOnly = true)
    public Usuarios recuperarPorId(long id) {
        return usuariosDao.recuperarPorId(id);
    }

    @Override
    public void atualizar(Usuarios usuario) {
        usuariosDao.atualizar(usuario);

    }

    @Override
    public void excluir(long id) {

        usuariosDao.excluir(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuarios recuperarPorUsername(String username) {

        return usuariosDao.recuperarPorUsername(username);
    }
}
