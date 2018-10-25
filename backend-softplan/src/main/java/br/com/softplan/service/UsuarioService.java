package br.com.softplan.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softplan.entity.ResponseEntity;
import br.com.softplan.entity.Usuario;
import br.com.softplan.repository.UsuarioRepository;

@Service 
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public ResponseEntity salvar(Usuario usuario)
	{
		ResponseEntity responseEntity;
		try {
			usuario.setCadastro(new Date());
			usuarioRepository.save(usuario);
			responseEntity = new ResponseEntity(1, "Registro salvo com sucesso");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			responseEntity = new ResponseEntity(4, "Erro ao salvar o registro");
			e.printStackTrace();
		}
		return responseEntity;
	}
	
	public Usuario findById(String id)
	{
		return usuarioRepository.findById(id).get();
	}
	
	public Usuario findByEmail(String email)
	{
		return usuarioRepository.findByEmail(email);
	}
	
	public ResponseEntity delete(String id) {
		ResponseEntity responseEntity;
		try {
			usuarioRepository.deleteById(id);
			responseEntity = new ResponseEntity(1, "Registro excluído com sucesso!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			responseEntity = new ResponseEntity(4, "Erro ao excluir o Registro");
			e.printStackTrace();
		}
		return responseEntity;
	}
	
	public List<Usuario> listAll()
	{
		return usuarioRepository.findAll();
	}

}
