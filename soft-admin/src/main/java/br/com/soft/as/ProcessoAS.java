/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.soft.as;

import br.com.soft.dao.GenericDao;
import com.soft.core.entidades.Processo;
import com.soft.core.entidades.Usuario;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Moquiuti
 */
@LocalBean
@Stateless
public class ProcessoAS {

    @EJB
    private GenericDao<Processo, Long> dao;

    private final Logger log = LogManager.getLogger(getClass());

    public Processo inserir(Processo processo) {
        return dao.salvar(processo);
    }

    public Processo atualizar(Processo processo) {
        return dao.atualizar(processo);
    }

    public List<Processo> getTodos() {
        return dao.getList(Processo.class);
    }

    public Processo getFindById(Long id) {
        return dao.findById(id, Processo.class);
    }

    public List<Processo> getTodosSemParecer() {
        String query = "select p.* from processo p where p.situacao = 'PENDENTE'";
        Query queryNative = dao.getEntityManager().createNativeQuery(query, Processo.class);
        List<Processo> processos = null;
        try {
            processos = (List<Processo>) queryNative.getResultList();
        } catch (NoResultException e) {
            log.error(" Falha na tentativa de busca de processo sem parecer {}, {}", e.getMessage());
        }
        return processos;
    }

    public void remover(Long id) {
        dao.remover(id, Usuario.class);
    }
}
