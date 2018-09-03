package br.com.renancelso.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import br.com.renancelso.padrao.GenericService;
import br.com.renancelso.service.model.Processo;
import br.com.renancelso.service.model.Usuario;
import br.com.renancelso.service.model.UsuarioProcesso;

/**
 * @author Renan Celso
 */
@Stateless
public class FinalizadorService extends GenericService implements FinalizadorServiceLocal {
	
	private static final long serialVersionUID = 1837109747098823027L;
	
	protected Logger log = Logger.getLogger(FinalizadorService.class.getName());

	@SuppressWarnings("unchecked")
	@Override
	public List<Processo> listarProcessosPendentesParecer(Usuario usuario) {
		List<Processo> listaProcessos = new ArrayList<>();
		try {
			List<Processo> listaProcessosAux = (List<Processo>) 
					consultarPorQuery("select o from Processo o where o.parecerValido is null or o.parecerValido = 'N'",0,0);
			
			for (Processo processo : listaProcessosAux) {				
				List<UsuarioProcesso> listaUsuarioProcesso = (List<UsuarioProcesso>) 
						consultarPorQuery("select o from UsuarioProcesso o where o.processo.id = "+processo.getId()+
										  " and o.usuario.id = "+usuario.getId(), 
										  0,0);				
				if(listaUsuarioProcesso != null && !listaUsuarioProcesso.isEmpty()){
					listaProcessos.add(processo);
				}
			}	
			
			return listaProcessos;
			
		} catch (Exception e) {
			return listaProcessos;
		}
	}

	

}
