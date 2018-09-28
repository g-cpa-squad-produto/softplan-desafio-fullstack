package br.com.desafiosoftplan.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.desafiosoftplan.model.Processo;
import br.com.desafiosoftplan.model.Usuario;
import br.com.desafiosoftplan.repository.ProcessoRepository;
import br.com.desafiosoftplan.repository.UsuarioRepository;

/**
 * Camada de serviço para entidade {@link Usuario}
 * @author Setembro/2018: Jessica Etiene Marques Almeida
 */
@Service
public class UsuarioService
{
   /*Injeção do repositório*/
   @Autowired
   private UsuarioRepository usuarioRepository;


   /**
    * Lista todos os usuarios
    * @return usuarios
    */
   public List<Usuario> listarTodos(){
      return usuarioRepository.findAll();
   }

   /**
    * Recupera usuario pelo Id
    * @param id
    * @return usuario
    */
   public Usuario buscarPorId(Long id){
      return usuarioRepository.findOne(id);
   }

   /**
    * Salva usuario
    * @param usuario
    * @return usuario
    */
   public Usuario salvar(Usuario usuario){
      return usuarioRepository.saveAndFlush(usuario);
   }

   /**
    * Exclui usuario
    * @param id
    */
   public void excluir(Long id){
      usuarioRepository.delete(id);
   }


   public Usuario buscaUsuarioPorLogin(String login){
      return usuarioRepository.findByLogin(login);
   }

   public List<Usuario> buscaUsuariosPassiveisParecer(){

      List<Usuario> usuarios = usuarioRepository.findFinalizadores();

      return usuarios;
   }
}
