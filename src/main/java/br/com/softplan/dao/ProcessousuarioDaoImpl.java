package br.com.softplan.dao;

import br.com.softplan.domain.Processousuario;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ProcessousuarioDaoImpl implements ProcessousuarioDao {

    @PersistenceContext
    private EntityManager em;


    @Override
    public void salvar(Processousuario processousuario) {
        em.persist(processousuario);
    }

    @Override
    public List<Processousuario> recuperarPorProcesso(long processoId) {
        return em.createQuery("select pu from Processousuario pu where pu.processo.id = :processoId", Processousuario.class)
                .setParameter("processoId", processoId)
                .getResultList();
    }

    @Override
    public Processousuario recuperarPorProcessoIdEUsuarioId(long processoId, long usuarioId) {
        return em.createQuery("select pu from Processousuario pu where pu.processo.id = :processoId and pu.usuario.id = :usuarioId", Processousuario.class)
                .setParameter("processoId", processoId)
                .setParameter("usuarioId", usuarioId)
                .getSingleResult();
    }

    @Override
    public void atualizar(Processousuario processousuario) {
        em.merge(processousuario);
    }

    @Override
    public void excluir(long processousuarioId) {
        em.remove(em.getReference(Processousuario.class, processousuarioId));
    }


}
