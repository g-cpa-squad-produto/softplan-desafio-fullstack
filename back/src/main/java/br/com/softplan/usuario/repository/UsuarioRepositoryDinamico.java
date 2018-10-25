package br.com.softplan.usuario.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.softplan.security.enums.PerfilEnum;
import br.com.softplan.usuario.modelos.Usuario;

/**
 * @author emanuel
 * 
 *         Classe utilizada para fazer requisicoes dinamicas
 */
@Repository
public class UsuarioRepositoryDinamico {
	@PersistenceContext
	@Autowired
	private EntityManager manager;

	public List<Usuario> listarUsuarios(PerfilEnum perfil) {
		List<Usuario> usuarios = null;
		try {
			String sql = "SELECT usuario FROM Usuario usuario WHERE usuario.deletado = false ";
			if (perfil != null) {
				sql += " AND  usuario.perfil = :perfil";
			}

			Query query = manager.createQuery(sql);
			if (perfil != null) {
				query.setParameter("perfil", perfil);
			}
			usuarios = query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			manager.close();
		}
		return usuarios;
	}

}
