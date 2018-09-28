package br.com.desafiosoftplan.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.desafiosoftplan.model.Processo;
import br.com.desafiosoftplan.repository.ProcessoRepository;

/**
 * Classe de serviço {@link Processo}
 * @author Setembro/2018: Jessica Etiene Marques Almeida
 */
@Service
public class ProcessoService
{
   /*Injeção do repositório*/
   @Autowired
   private ProcessoRepository processoRepository;


   /**
    * Lista todos os processos
    * @return processos
    */
   public List<Processo> listarTodos(){
      return processoRepository.findAll();
   }

   /**
    * Recupera processo pelo Id
    * @param id
    * @return processo
    */
   public Processo buscarPorId(Long id){
      return processoRepository.findOne(id);
   }

   /**
    * Salva processo
    * @param processo
    * @return processo
    */
   public Processo salvar(Processo processo){
      return processoRepository.saveAndFlush(processo);
   }

   /**
    * Exclui processo
    * @param id
    */
   public void excluir(Long id){
      processoRepository.delete(id);
   }
}
