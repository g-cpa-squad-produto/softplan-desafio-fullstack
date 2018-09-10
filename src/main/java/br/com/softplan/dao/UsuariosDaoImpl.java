package br.com.softplan.dao;

import br.com.softplan.domain.Usuarios;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.List;

@Repository
public class UsuariosDaoImpl implements UsuariosDao {


    @PersistenceContext
    private EntityManager em;


    @Override
    public void salvar(Usuarios usuario) {

        em.persist(usuario);
    }

    @Override
    public List<Usuarios> recuperar() {

        return em.createQuery("select u from Usuarios u", Usuarios.class).getResultList();

    }

    @Override
    public Usuarios recuperarPorId(long id) {

        return em.find(Usuarios.class, id);
    }

    @Override
    public void atualizar(Usuarios usuario) {

        em.merge(usuario);
    }

    @Override
    public void excluir(long id) {

        em.remove(em.getReference(Usuarios.class, id));
    }

    @Override
    public Usuarios recuperarPorUsername(String username) {

        try {

            Usuarios usuarios = em.createQuery("select u from Usuarios u where u.username = :userName", Usuarios.class)
                    .setParameter("userName", username).getSingleResult();

            if(usuarios == null){
                return null;
            }else{
                return usuarios;
            }

        }catch (PersistenceException pe){
            return null;
        }

    }
}
