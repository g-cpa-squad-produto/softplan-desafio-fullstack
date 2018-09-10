package br.com.softplan.dao;

import br.com.softplan.domain.Processo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ProcessoDaoImpl implements ProcessoDao {

    @PersistenceContext
    private EntityManager em;


    @Override
    public void salvar(Processo processo) {

        em.persist(processo);
    }

    @Override
    public List<Processo> recuperar() {

        return em.createQuery("select p from Processo p", Processo.class).getResultList();

    }

    @Override
    public Processo recuperarPorId(long id) {

        return em.find(Processo.class, id);
    }

    @Override
    public void atualizar(Processo processo) {

        em.merge(processo);
    }

    @Override
    public void excluir(long id) {

        em.remove(em.getReference(Processo.class, id));
    }

}
