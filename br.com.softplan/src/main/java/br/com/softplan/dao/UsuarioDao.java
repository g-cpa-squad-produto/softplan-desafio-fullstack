package br.com.softplan.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.softplan.modelo.Usuario;

public class UsuarioDao {

	EntityManager em;
	public UsuarioDao() {
	}
	
    public Usuario salvar(Usuario usuario) {
    	em = EntityManagerUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();
        em.close();
        return usuario;
    }
    
    public List<Usuario> listar() {
    	em = EntityManagerUtil.getEntityManager();
        em.getTransaction().begin();
    	System.out.println("iria filtrar tem factory: "+em);
    	List<Usuario> lista = em.createQuery("from Usuario").getResultList();
    	em.getTransaction().commit();
    	em.close();
		for (Usuario u : lista){
			System.out.println(u.getId() + " -; " + u.getNome() + " -; " + u.getIdade());
		}
		
		
		
		return lista;
    }
}