/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.soft.dao;

import java.io.Serializable;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author Moquiuti
 * @param <T>
 * @param <I>
 */
@LocalBean
@Stateless
public class GenericDao<T, I extends Serializable> {

    @PersistenceContext(name = "softPlanPU")
    private EntityManager em;

    public EntityManager getEntityManager() {
        return this.em;
    }

    public T salvar(T entity) {
        getEntityManager().persist(entity);
        return entity;
    }

    public T atualizar(T entity) {
        getEntityManager().merge(entity);
        return entity;
    }

    public void remover(I id, Class classe) {
        T entity = findById(id, classe);
        T mergedEntity = getEntityManager().merge(entity);
        getEntityManager().remove(mergedEntity);
    }

    public List<T> getList(Class persistedClass) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(persistedClass);
        query.from(persistedClass);
        return getEntityManager().createQuery(query).getResultList();
    }

    public T findById(I id, Class persistedClass) {
        return (T) getEntityManager().find(persistedClass, id);
    }

}
