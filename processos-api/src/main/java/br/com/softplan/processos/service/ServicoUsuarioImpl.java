package br.com.softplan.processos.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softplan.processos.dao.UsuarioDAO;
import br.com.softplan.processos.exception.GenericException;
import br.com.softplan.processos.exception.RegraNegocioException;
import br.com.softplan.processos.model.Usuario;

@Service
public class ServicoUsuarioImpl implements ServicoUsuario {

    @Autowired
    private UsuarioDAO usuarioDAO;

    @Override
    public List<Usuario> selecionarTodos() throws GenericException {
	try {
	    return usuarioDAO.findAll();
	} catch (Exception e) {
	    throw new GenericException("Falha ao carregar os usuários");
	}
    }

    @Override
    public Usuario selecionarUsuarioPorId(Long id) throws GenericException {
	try {
	    return usuarioDAO.findOne(id);
	} catch (Exception e) {
	    throw new GenericException("Falha ao ler informações do usuário");
	}
    }

    @Override
    public Usuario adicionarUsuario(Usuario usuario) throws GenericException {
	try {
	    // Verifica se já existe algum usuário cadastrado com o e-mail
	    if (usuarioDAO.findByEmail(usuario.getEmail()) == null) {
		return usuarioDAO.save(usuario);
	    } else {
		throw new RegraNegocioException("E-mail já está sendo utilizado");
	    }
	} catch (RegraNegocioException e) {
	    throw e;
	} catch (Exception e) {
	    throw new GenericException("Falha ao tentar cadastrar usuário");
	}
    }

    @Override
    public Usuario atualizarUsuario(Long id, Usuario usuario) throws GenericException {
	try {
	    Usuario usuarioSalvo = this.selecionarUsuarioPorId(id);

	    // Verifica se o usuário existe
	    if (usuarioSalvo == null) {
		throw new RegraNegocioException("Usuário não encontrado");
	    }

	    // Copia os novos valores para o usuário
	    BeanUtils.copyProperties(usuario, usuarioSalvo, "codigo", "email");

	    return usuarioDAO.save(usuario);
	} catch (RegraNegocioException e) {
	    throw e;
	} catch (Exception e) {
	    throw new GenericException("Falha ao tentar atualizar o usuário");
	}
    }

    @Override
    public void excluirUsuario(Long id) throws GenericException {
	try {
	    Usuario usuario = usuarioDAO.findOne(id);
	    // Verifica se o usuario existe e se não é usuário ADMIN
	    if (usuario != null && !usuario.isAdministrador()) {
		usuarioDAO.delete(id);
	    } else {
		throw new RegraNegocioException("Usuário não encontrado ou não pode ser excluído");
	    }
	} catch (RegraNegocioException e) {
	    throw e;
	} catch (Exception e) {
	    throw new GenericException("Ocorreu um erro ao tentar excluir o usuário");
	}
    }
}
