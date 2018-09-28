package br.com.desafiosoftplan.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import br.com.desafiosoftplan.model.Parecer;
import br.com.desafiosoftplan.model.Processo;
import br.com.desafiosoftplan.model.Usuario;
import br.com.desafiosoftplan.repository.ParecerRepository;

/**
 * Classe de serviço {@link Parecer}
 * @author Setembro/2018: Jessica Etiene Marques Almeida
 */
@Service
public class ParecerService
{
   @Autowired
   private ParecerRepository parecerRepository;

   public List<Parecer> buscarProcessosPendentes(Usuario usuario){
      return parecerRepository.buscarProcessosPendentes(usuario);
   }


   /**
    * Lista todos os processos
    * @return processos
    */
   public List<Parecer> listarTodos(){
      return parecerRepository.findAll();
   }

   /**
    * Recupera processo pelo Id
    * @param id
    * @return processo
    */
   public Parecer buscarPorId(Long id){
      return parecerRepository.findOne(id);
   }

   /**
    * Salva processo
    * @param parecer
    * @return processo
    */
   public Parecer salvar(Parecer parecer){
      return parecerRepository.saveAndFlush(parecer);
   }

   /**
    * Exclui processo
    * @param id
    */
   public void excluir(Long id){
      parecerRepository.delete(id);
   }
}
