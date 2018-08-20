package com.processos.luiz.service;

import java.util.ArrayList;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.processos.luiz.exception.UsuarioException;
import com.processos.luiz.models.Role;
import com.processos.luiz.models.Usuario;
import com.processos.luiz.repository.RoleRepository;
import com.processos.luiz.repository.UsuarioRepository;
import java.util.List;

@Service
@Transactional
public class UsuarioService {
	private final Logger LOG = LoggerFactory.getLogger(UsuarioService.class);
	private final String NOME_ROLE_USER = "ROLE_USER";
	private final String SENHA_DEFALUT_NOVOS_USUARIOS = "processos";
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private RoleRepository roleRepository;
	
	public Usuario salvarUsuario(Usuario usuario) throws UsuarioException{
		Usuario usuarioExistente = usuarioRepository.findByLogin(usuario.getLogin());
		if(usuarioExistente == null){
			setarDadosIniciaisUsuario(usuario);
		}else{
			usuario.setCodigo(usuarioExistente.getCodigo());
			usuario.setSenha(usuarioExistente.getSenha());
			usuario.setDataCadastro(usuarioExistente.getDataCadastro());
		}
		setarPerfisUsuario(usuario);
		usuarioRepository.save(usuario);
		return usuario;
	}
	private void setarDadosIniciaisUsuario(Usuario usuario){
		usuario.setSenha(SENHA_DEFALUT_NOVOS_USUARIOS);
		usuario.setDataCadastro(new Date());
	}
	private String hashSenha(String senha){
		BCryptPasswordEncoder cryptPasswordEncoder = new BCryptPasswordEncoder();
		return cryptPasswordEncoder.encode(senha);
	}
	private void setarPerfisUsuario(Usuario usuario){
		List<Role> authorities = new ArrayList<Role>();
		boolean temPerfilUsuario = false;
		if(!usuario.getRoles().isEmpty()){
			for(Role role: usuario.getRoles()){
				if(NOME_ROLE_USER.equals(role.getNomeRole())){
					temPerfilUsuario = true;
				}
				authorities.add(roleRepository.findByNomeRole(role.getNomeRole()));
			}
		}
		if(!temPerfilUsuario){
			authorities.add(roleRepository.findByNomeRole(NOME_ROLE_USER));
		}
		usuario.setRoles(authorities);
	}
	public String alterarSenhaUsuario(String login,String senhaNova) throws UsuarioException{
		Usuario usuarioExistente = usuarioRepository.findByLogin(login);
		if(usuarioExistente == null){
			String mensagemErro = "Login não confere, processo abortado";
			LOG.info(mensagemErro);
			return mensagemErro;
		}
		usuarioExistente.setSenha(hashSenha(senhaNova));
		return "Senha alterada com sucesso";
	}
}
